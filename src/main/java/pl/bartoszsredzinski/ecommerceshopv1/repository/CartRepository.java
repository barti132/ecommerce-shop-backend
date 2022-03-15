package pl.bartoszsredzinski.ecommerceshopv1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.Cart;

import java.util.Optional;

/**
 * Cart crud repository
 *
 * @author Bartosz Średziński
 * created on 03.03.2022
 */
@Repository
public interface CartRepository extends CrudRepository<Cart, Long>{

    @Query("SELECT cart FROM Cart cart JOIN FETCH cart.products products WHERE cart.id = :id")
    Optional<Cart> getFullCartByID(@Param("id") Long id);
}
