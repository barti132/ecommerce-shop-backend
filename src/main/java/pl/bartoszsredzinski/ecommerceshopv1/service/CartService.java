package pl.bartoszsredzinski.ecommerceshopv1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartoszsredzinski.ecommerceshopv1.PDFGenerator;
import pl.bartoszsredzinski.ecommerceshopv1.dto.CartDto;
import pl.bartoszsredzinski.ecommerceshopv1.dto.request.CartItemRequest;
import pl.bartoszsredzinski.ecommerceshopv1.dto.request.OrderRequest;
import pl.bartoszsredzinski.ecommerceshopv1.exception.InvalidIdException;
import pl.bartoszsredzinski.ecommerceshopv1.mapper.CartMapper;
import pl.bartoszsredzinski.ecommerceshopv1.model.*;
import pl.bartoszsredzinski.ecommerceshopv1.model.Address;
import pl.bartoszsredzinski.ecommerceshopv1.model.Cart;
import pl.bartoszsredzinski.ecommerceshopv1.model.CartItem;
import pl.bartoszsredzinski.ecommerceshopv1.repository.*;
import pl.bartoszsredzinski.ecommerceshopv1.service.auth.AuthService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Cart service
 *
 * @author Bartosz Średziński
 * created on 03.03.2022
 */
@Service
@AllArgsConstructor
public class CartService{
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final StockRepository stockRepository;
    private final InvoiceRepository invoiceRepository;
    private final AuthService authService;
    private final CartMapper cartMapper;
    private final PDFGenerator pdfGenerator;

    public CartDto getCartData(String login){
        User user = authService.getCurrentUser(login);

        if(user.getCart() == null){
            return null;
        }
        Cart cart = cartRepository.getFullCartByID(user.getCart().getId()).orElseThrow(() -> new InvalidIdException("Invalid cart id"));
        return cartMapper.cartToCartDto(cart);
    }

    @Transactional
    public void addItemToCart(String login, CartItemRequest item){
        User user = authService.getCurrentUser(login);

        CartItem cartItem = new CartItem();
        cartItem.setProduct(productRepository.findById(item.getProductId()).orElseThrow(() -> new InvalidIdException("Wrong product id")));
        cartItem.setAmount(item.getAmount());

        if(user.getCart() == null){
            createNewCart(user);
        }

        if(!findProductInCartAndUpdate(user, cartItem)){
            cartItemRepository.save(cartItem);
            addItemData(user, cartItem);
        }

        userRepository.save(user);
    }

    private boolean findProductInCartAndUpdate(User user, CartItem cartItem){
        Cart cart = user.getCart();
        for(CartItem i : cart.getProducts()){
            if(i.getProduct().getId().equals(cartItem.getProduct().getId())){
                i.setAmount(i.getAmount() + cartItem.getAmount());
                updateCart(cart, cartItem);

                cartRepository.save(cart);
                return true;
            }
        }
        return false;
    }

    private void updateCart(Cart cart, CartItem cartItem){
        cart.setTotalItems(cart.getTotalItems() + cartItem.getAmount());
        cart.setTotalPriceGross(cart.getTotalPriceGross().add(cartItem.getProduct().getPriceGross().multiply(new BigDecimal(cartItem.getAmount()))));
        cart.setTotalPriceNet(cart.getTotalPriceNet().add(cartItem.getProduct().getPriceNet().multiply(new BigDecimal(cartItem.getAmount()))));
        cart.setUpdatedDate(new Date(System.currentTimeMillis()));
    }

    private void addItemData(User user, CartItem cartItem){
        Cart cart = user.getCart();
        cart.setTotalPriceGross(cart.getTotalPriceGross().add(cartItem.getProduct().getPriceGross().multiply(new BigDecimal(cartItem.getAmount()))));
        cart.setTotalPriceNet(cart.getTotalPriceNet().add(cartItem.getProduct().getPriceNet().multiply(new BigDecimal(cartItem.getAmount()))));
        cart.setTotalItems(cart.getTotalItems() + cartItem.getAmount());
        cart.setUpdatedDate(new Date(System.currentTimeMillis()));
        cart.getProducts().add(cartItem);
        cartRepository.save(cart);
    }

    private void createNewCart(User user){
        Cart cart = new Cart();
        cart.setProducts(new ArrayList<>());
        cart.setTotalItems(0);
        cart.setTotalPriceGross(new BigDecimal(0));
        cart.setTotalPriceNet(new BigDecimal(0));
        cart = cartRepository.save(cart);
        user.setCart(cart);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserCart(String login){
        User user = authService.getCurrentUser(login);

        if(user.getCart() != null){
            Cart cart = user.getCart();

            user.setCart(null);
            userRepository.save(user);

            cartItemRepository.deleteAll(cart.getProducts());
            cart.getProducts().clear();

            cartRepository.delete(cart);
        }
    }

    @Transactional
    public void deleteCartItemFromUserCart(String login, Integer id){
        User user = authService.getCurrentUser(login);

        if(user.getCart() != null){
            Cart cart = user.getCart();
            CartItem item = cart.getProducts().get(id);


            cart.setUpdatedDate(new Date(System.currentTimeMillis()));
            cart.setTotalItems(cart.getTotalItems() - item.getAmount());
            cart.setTotalPriceGross(cart.getTotalPriceGross().subtract(item.getProduct().getPriceGross().multiply(new BigDecimal(item.getAmount()))));
            cart.setTotalPriceNet(cart.getTotalPriceNet().subtract(item.getProduct().getPriceNet().multiply(new BigDecimal(item.getAmount()))));

            cart.getProducts().remove(item);
            cartItemRepository.delete(item);
            cartRepository.save(cart);
        }
    }

    @Transactional
    public byte[] makeOrder(String login, OrderRequest orderRequest){
        User user = authService.getCurrentUser(login);
        Address address = addressRepository.findById(orderRequest.getAddressId()).orElseThrow(() -> new InvalidIdException("Invalid address id"));
        Cart cart = user.getCart();

        validateCartItems(cart.getProducts());

        updateStock(cart.getProducts());

        Invoice invoice = createInvoice(user, address, cart, orderRequest);
        return pdfGenerator.generateOrderInvoicePDF(invoice);
    }

    private Invoice createInvoice(User user, Address address, Cart cart, OrderRequest orderRequest){
        Invoice invoice = new Invoice();

        invoice.setInvoiceDate(new Date(System.currentTimeMillis()));
        invoice.setAddress(address);
        invoice.setProducts(cart.getProducts());
        invoice.setUser(user);
        invoice.setTotalItems(cart.getTotalItems());
        invoice.setTotalPriceGross(cart.getTotalPriceGross());
        invoice.setTotalPriceNet(cart.getTotalPriceNet());
        invoice.setCartNumber(orderRequest.getCartNumber());
        invoice.setCardName(orderRequest.getCardName());

        invoiceRepository.save(invoice);

        cart.setProducts(new ArrayList<>());
        user.setCart(null);
        userRepository.save(user);
        cartRepository.delete(cart);

        return invoice;
    }

    private void updateStock(List<CartItem> products){
        for(CartItem item: products){
            Stock stock = stockRepository.findByProduct(item.getProduct()).orElseThrow(()-> new RuntimeException("Wrong product"));
            stock.setAmount(stock.getAmount() - item.getAmount());
            stock.setUpdatedDate(new Date(System.currentTimeMillis()));
        }
    }

    private void validateCartItems(List<CartItem> products){
        for(CartItem item: products){
            Stock stock = stockRepository.findByProduct(item.getProduct()).orElseThrow(()-> new RuntimeException("Wrong product"));
            if(item.getAmount() > stock.getAmount()){
                throw new RuntimeException("Invalid amount of product - " + item.getProduct().getName());
            }
        }
    }
}
