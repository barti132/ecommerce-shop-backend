package pl.bartoszsredzinski.ecommerceshopv1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserDto;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 24.02.2022
 */
@Mapper(componentModel = "spring")
public interface UserMapper{

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);
}
