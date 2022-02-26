package pl.bartoszsredzinski.ecommerceshopv1.service.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartoszsredzinski.ecommerceshopv1.model.RefreshToken;
import pl.bartoszsredzinski.ecommerceshopv1.repository.RefreshTokenRepository;

import java.time.Instant;
import java.util.UUID;

/**
 * Refresh token service
 *
 * @author Bartosz Średziński
 * created on 26.02.2022
 */
@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService{

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    public void validateRefreshToken(String token){
        refreshTokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid refresh token"));
    }

    public void deleteRefreshToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }
}
