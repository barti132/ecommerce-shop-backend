package pl.bartoszsredzinski.ecommerceshopv1.service.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;
import pl.bartoszsredzinski.ecommerceshopv1.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Bartosz Średziński
 * created on 01.03.2022
 */
@SpringBootTest
@ActiveProfiles("test")
class UserDetailServiceImplTest{

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init(){
        User user1 = User.builder().email("mail1").login("login1").password("password1").name("name1").lastName("lastname1")
                .role("user").enabled(true).build();
        User user2 = User.builder().email("mail2").login("login2").password("password2").name("name2").lastName("lastname2")
                .role("user").enabled(true).build();

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    public void loadUserByUsername_should_return_UserDetails(){
        UserDetails user = userDetailService.loadUserByUsername("login1");
        assertEquals("login1", user.getUsername());
        assertEquals("password1", user.getPassword());
        user = userDetailService.loadUserByUsername("login2");
        assertEquals("login2", user.getUsername());
        assertEquals("password2", user.getPassword());
    }

    @Test
    public void loadUserByUsername_should_throw_exception(){
        assertThrows(UsernameNotFoundException.class, ()-> userDetailService.loadUserByUsername("login"));
        assertThrows(UsernameNotFoundException.class, ()-> userDetailService.loadUserByUsername("username"));
    }

}