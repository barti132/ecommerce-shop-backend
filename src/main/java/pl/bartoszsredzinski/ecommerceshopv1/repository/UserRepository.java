package pl.bartoszsredzinski.ecommerceshopv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;

import java.util.Optional;

/**
 * User JPA Repository
 *
 * @author Bartosz Średziński
 * created on 23.02.2022
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByLogin(String login);
}
