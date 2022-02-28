package pl.bartoszsredzinski.ecommerceshopv1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserDto;
import pl.bartoszsredzinski.ecommerceshopv1.mapper.UserMapper;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;
import pl.bartoszsredzinski.ecommerceshopv1.repository.UserRepository;
import pl.bartoszsredzinski.ecommerceshopv1.service.auth.AuthService;

/**
 * User service
 *
 * @author Bartosz Średziński
 * created on 24.02.2022
 */
@Service
@AllArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public UserDto getUserByID(Integer id){
        User user = userRepository.getById(id);
        return  userMapper.userToUserDto(user);
    }

    @Transactional(readOnly = true)
    public UserDto getCurrentUser(){
        User user = authService.getCurrentUser();
        return userMapper.userToUserDto(user);
    }
}
