package pl.bartoszsredzinski.ecommerceshopv1.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import pl.bartoszsredzinski.ecommerceshopv1.dto.PasswordDto;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserDto;
import pl.bartoszsredzinski.ecommerceshopv1.model.Address;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;
import pl.bartoszsredzinski.ecommerceshopv1.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 04.03.2022
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class UserServiceTest{

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;

    @BeforeAll
    void init(){
        user1 = User.builder().email("email").login("spring").password("password").name("name").lastName("lastName").role("user").enabled(true).build();
        user2 = User.builder().email("email1").login("spring1").password("password1").name("name1").lastName("lastName1").role("user1").enabled(true).build();

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @WithMockUser(username="spring")
    @Test
    public void getCurrentUser_should_return_UserDto(){
        assertNotNull(userService.getCurrentUserDto());
        assertEquals("spring", userService.getCurrentUserDto().getLogin());
    }

    @WithMockUser(username="spring2")
    @Test
    public void getCurrentUser_should_return_exception(){
        assertThrows(UsernameNotFoundException.class, ()-> userService.getCurrentUserDto());
    }

    @WithMockUser(username="spring")
    @Test
    public void updateCurrentUser_should_update_springUser(){
        UserDto u = new UserDto("email", "spring", "name", "lastName", "123456", null);
        assertEquals("123456", userService.updateCurrentUser(u).getPhoneNumber());

        u = new UserDto("email@email", "spring", "name", "lastName", "123456", null);
        assertEquals("email@email", userService.updateCurrentUser(u).getEmail());
    }

    @WithMockUser(username="spring")
    @Test
    public void addAddressToCurrentUser_should_work(){
        Address ad = new Address(1L, "address", "city", "country", "postalCode");

        userService.addAddressToCurrentUser(ad);

        assertEquals(1, userRepository.findByLogin("spring").get().getAddresses().size());
        assertEquals("address", userRepository.findByLogin("spring").get().getAddresses().get(0).getAddress());
    }

    @WithMockUser(username="spring")
    @Test
    public void deleteAddressFromCurrentUser_should_work(){
        Address ad = new Address(1L, "address", "city", "country", "postalCode");

        userService.addAddressToCurrentUser(ad);

        assertEquals(1, userRepository.findByLogin("spring").get().getAddresses().size());
        assertEquals("address", userRepository.findByLogin("spring").get().getAddresses().get(0).getAddress());

        userService.deleteAddressFromCurrentUser(1L);
        assertEquals(0, userRepository.findByLogin("spring").get().getAddresses().size());
    }

    @WithMockUser(username="spring")
    @Test
    public void changePassword_should_change_user_password(){
        userService.changePassword(new PasswordDto("haslo"));
        assertNotEquals("password", userRepository.findByLogin("spring").get().getPassword());
    }
}