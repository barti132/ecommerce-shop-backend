package pl.bartoszsredzinski.ecommerceshopv1.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 01.03.2022
 */
@SpringBootTest
@ActiveProfiles("test")
class JwtProviderTest{

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    public void generateTokenWithLoginAndRole_should_work(){
        assertNotNull(jwtProvider.generateTokenWithLoginAndRole("login", "user"));
        assertTrue(jwtProvider.generateTokenWithLoginAndRole("login1", "user").length() > 400);
        assertNotNull(jwtProvider.generateTokenWithLoginAndRole("login2", "admin").length());
    }
}