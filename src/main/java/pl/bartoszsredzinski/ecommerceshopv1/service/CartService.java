package pl.bartoszsredzinski.ecommerceshopv1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.ecommerceshopv1.dto.CartDto;
import pl.bartoszsredzinski.ecommerceshopv1.mapper.CartMapper;
import pl.bartoszsredzinski.ecommerceshopv1.model.Cart;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;
import pl.bartoszsredzinski.ecommerceshopv1.repository.CartItemRepository;
import pl.bartoszsredzinski.ecommerceshopv1.repository.CartRepository;
import pl.bartoszsredzinski.ecommerceshopv1.service.auth.AuthService;

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
    private final AuthService authService;
    private final CartMapper cartMapper;

    public CartDto getCartData(String login){
        User user = authService.getCurrentUser();
        Cart cart = cartRepository.findByUser(user).orElse(null);
        if(cart != null){
            return cartMapper.cartToCartDto(cart);
        }
        return null;
    }
}
