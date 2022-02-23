package pl.bartoszsredzinski.ecommerceshopv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;

import java.util.List;

/**
 * Product JPA Repository
 *
 * @author Bartosz Bartosz Średziński
 */

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer>{
    List<Product> findAllByNameContainsIgnoreCase(String name);

    List<Product> findAllByNameContainsIgnoreCaseAndCategoryContainsIgnoreCase(String name, String category);

    List<Product> findAllByCategory(String category);

    @Query("SELECT DISTINCT p.producer_name FROM Product p WHERE p.category = :cat")
    List<String> getProducersByCategory(@Param("cat") String category);
}
