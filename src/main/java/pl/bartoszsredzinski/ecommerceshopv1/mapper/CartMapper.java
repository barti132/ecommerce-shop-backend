package pl.bartoszsredzinski.ecommerceshopv1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.bartoszsredzinski.ecommerceshopv1.dto.CartDto;
import pl.bartoszsredzinski.ecommerceshopv1.model.Cart;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 03.03.2022
 */
@Mapper(componentModel = "spring")
public interface CartMapper{
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDto cartToCartDto(Cart cart);
}
