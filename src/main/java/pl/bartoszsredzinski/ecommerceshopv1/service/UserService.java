package pl.bartoszsredzinski.ecommerceshopv1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserDto;
import pl.bartoszsredzinski.ecommerceshopv1.mapper.UserMapper;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;
import pl.bartoszsredzinski.ecommerceshopv1.repository.UserRepository;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 24.02.2022
 */
@Service
@AllArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getUserByID(Integer id){
        User user = userRepository.getById(id);
        return  userMapper.userToUserDto(user);
    }

}
