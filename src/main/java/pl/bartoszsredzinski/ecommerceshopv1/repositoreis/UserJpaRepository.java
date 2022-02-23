package pl.bartoszsredzinski.ecommerceshopv1.repositoreis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;

/**
 * User JPA Repository
 *
 * @author Bartosz Średziński
 * created on 23.02.2022
 */
@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer>{
}
