package pl.bartoszsredzinski.ecommerceshopv1.service.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.bartoszsredzinski.ecommerceshopv1.model.RefreshToken;
import pl.bartoszsredzinski.ecommerceshopv1.repository.RefreshTokenRepository;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bartosz Średziński
 * created on 01.03.2022
 */
@SpringBootTest
@ActiveProfiles("test")
class RefreshTokenServiceTest{

    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    void init(){
        RefreshToken token1 = new RefreshToken(1L, "token1", Instant.now());
        RefreshToken token2 = new RefreshToken(2L, "token2", Instant.now());
        RefreshToken token3 = new RefreshToken(3L, "token3", Instant.now());
        refreshTokenRepository.save(token1);
        refreshTokenRepository.save(token2);
        refreshTokenRepository.save(token3);
    }

    @Test
    public void generateRefreshToken_should_return_token(){
        assertNotNull(refreshTokenService.generateRefreshToken());
    }

    @Test
    public void validateRefreshToken_should_throw_exception(){
        assertThrows(RuntimeException.class, ()-> refreshTokenService.validateRefreshToken("not_valid"));
        assertThrows(RuntimeException.class, ()-> refreshTokenService.validateRefreshToken("testingToken"));
    }

}