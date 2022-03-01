package pl.bartoszsredzinski.ecommerceshopv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bartoszsredzinski.ecommerceshopv1.model.RefreshToken;

import java.util.Optional;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 26.02.2022
 */

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
