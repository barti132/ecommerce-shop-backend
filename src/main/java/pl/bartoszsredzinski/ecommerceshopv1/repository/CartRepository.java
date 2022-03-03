package pl.bartoszsredzinski.ecommerceshopv1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.Cart;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;

import java.util.Optional;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 03.03.2022
 */
@Repository
public interface CartRepository extends CrudRepository<Cart, Long>{
    Optional<Cart> findByUser(User user);
}
