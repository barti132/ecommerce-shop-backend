package pl.bartoszsredzinski.ecommerceshopv1.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartoszsredzinski.ecommerceshopv1.dto.PasswordDto;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserDto;
import pl.bartoszsredzinski.ecommerceshopv1.mapper.UserMapper;
import pl.bartoszsredzinski.ecommerceshopv1.model.Address;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;
import pl.bartoszsredzinski.ecommerceshopv1.repository.AddressRepository;
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
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

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

    @Transactional
    public void addAddressToCurrentUser(Address address){
        User user = authService.getCurrentUser();
        Address add = Address.builder().address(address.getAddress()).city(address.getCity())
                .country(address.getCountry()).postalCode(address.getPostalCode()).build();

        addressRepository.save(add);

        user.getAddresses().add(add);
        userRepository.save(user);
    }

    @Transactional
    public void deleteAddressFromCurrentUser(Integer id){
        User user = authService.getCurrentUser();
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid address id"));
        user.getAddresses().remove(user.getAddresses().indexOf(address));
        addressRepository.delete(address);
    }

    @Transactional
    public UserDto updateCurrentUser(UserDto userDto){
        User user = authService.getCurrentUser();
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        user.setLastName(userDto.getLastName());
        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());

        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Transactional
    public void changePassword(PasswordDto passwordDto){
        User user = authService.getCurrentUser();
        user.setPassword(passwordEncoder.encode(passwordDto.getPassword()));
        userRepository.save(user);
    }
}
