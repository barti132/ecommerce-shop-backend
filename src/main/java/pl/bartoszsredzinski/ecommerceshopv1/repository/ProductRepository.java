package pl.bartoszsredzinski.ecommerceshopv1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;

import java.util.List;

/**
 * Product JPA Repository
 *
 * @author Bartosz Bartosz Średziński
 */

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>{

    @Query("SELECT DISTINCT p.producerName FROM Product p WHERE p.category = :cat")
    List<String> getProducersByCategory(@Param("cat") String category);
}
