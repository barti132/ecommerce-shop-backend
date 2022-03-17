package pl.bartoszsredzinski.ecommerceshopv1.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartoszsredzinski.ecommerceshopv1.dto.PasswordDto;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserAdminDto;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserDto;
import pl.bartoszsredzinski.ecommerceshopv1.dto.request.UserStatusRequest;
import pl.bartoszsredzinski.ecommerceshopv1.exception.InvalidIdException;
import pl.bartoszsredzinski.ecommerceshopv1.mapper.UserMapper;
import pl.bartoszsredzinski.ecommerceshopv1.model.Address;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;
import pl.bartoszsredzinski.ecommerceshopv1.repository.AddressRepository;
import pl.bartoszsredzinski.ecommerceshopv1.repository.UserRepository;
import pl.bartoszsredzinski.ecommerceshopv1.service.auth.AuthService;

import java.util.ArrayList;
import java.util.List;

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
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserDto getCurrentUserDto(String login){
        User user = authService.getCurrentUser(login);
        return userMapper.userToUserDto(user);
    }

    @Transactional
    public void addAddressToCurrentUser(String login, Address address){
        User user = authService.getCurrentUser(login);
        Address add = Address.builder()
                .address(address.getAddress())
                .city(address.getCity())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .build();

        addressRepository.save(add);

        user.getAddresses().add(add);
        userRepository.save(user);
    }

    @Transactional
    public void deleteAddressFromCurrentUser(String login, Long id){
        User user = authService.getCurrentUser(login);
        Address address = addressRepository.findById(id).orElseThrow(() -> new InvalidIdException("Invalid address id"));

        if(user.getAddresses().remove(address)){
            addressRepository.delete(address);
        }
    }

    @Transactional
    public UserDto updateCurrentUser(String login, UserDto userDto){
        User user = authService.getCurrentUser(login);
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        user.setLastName(userDto.getLastName());
        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());

        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Transactional
    public void changePassword(String login, PasswordDto passwordDto){
        User user = authService.getCurrentUser(login);
        user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
        userRepository.save(user);
    }

    public List<UserAdminDto> getUserDataAdmin(){
        List<User> userList = userRepository.findAll();


        ArrayList<UserAdminDto> userArrayList = new ArrayList<>();
        for(User u : userList){
            userArrayList.add(userMapper.userToUserAdminDto(u));
        }

        return userArrayList;
    }

    @Transactional
    public void changeUserStatus(UserStatusRequest userStatusRequest){
        User user = userRepository.findById(userStatusRequest.getId())
                .orElseThrow(() -> new InvalidIdException("User " + userStatusRequest.getId() + " not found."));

        user.setEnabled(userStatusRequest.getStatus());
        userRepository.save(user);
    }
}
