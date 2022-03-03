package pl.bartoszsredzinski.ecommerceshopv1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartoszsredzinski.ecommerceshopv1.dto.CartDto;
import pl.bartoszsredzinski.ecommerceshopv1.dto.CartItemRequest;
import pl.bartoszsredzinski.ecommerceshopv1.mapper.CartMapper;
import pl.bartoszsredzinski.ecommerceshopv1.model.Cart;
import pl.bartoszsredzinski.ecommerceshopv1.model.CartItem;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;
import pl.bartoszsredzinski.ecommerceshopv1.repository.CartItemRepository;
import pl.bartoszsredzinski.ecommerceshopv1.repository.CartRepository;
import pl.bartoszsredzinski.ecommerceshopv1.repository.ProductRepository;
import pl.bartoszsredzinski.ecommerceshopv1.repository.UserRepository;
import pl.bartoszsredzinski.ecommerceshopv1.service.auth.AuthService;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Class description
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
    private final AuthService authService;
    private final CartMapper cartMapper;

    public CartDto getCartData(){
        User user = authService.getCurrentUser();

        Cart cart = cartRepository.getFullCartByID(user.getCart().getId());
        if(cart != null){
            return cartMapper.cartToCartDto(cart);
        }
        return null;
    }

    @Transactional
    public void addItemToCart(CartItemRequest item){
        User user = authService.getCurrentUser();

        CartItem cartItem = new CartItem();
        cartItem.setProduct(productRepository.findById(item.getProductId()).orElseThrow(() -> new RuntimeException("Wrong product id")));
        cartItem.setAmount(item.getAmount());
        cartItemRepository.save(cartItem);

        if(user.getCart() == null){
            createNewCart(user);
        }

        updateCartData(user, cartItem);


        user.getCart().getProducts().add(cartItem);
        userRepository.save(user);
    }

    private void updateCartData(User user, CartItem cartItem){
        Cart cart = user.getCart();
        cart.setUpdatedDate(new Date(System.currentTimeMillis()));
        cart.setTotalPriceGross(cart.getTotalPriceGross().add(cartItem.getProduct().getPriceGross()));
        cart.setTotalPriceNet(cart.getTotalPriceNet().add(cartItem.getProduct().getPriceNet()));
        cart.setTotalItems(cart.getTotalItems() + cartItem.getAmount());
        cart.setUpdatedDate(new Date(System.currentTimeMillis()));
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
}
