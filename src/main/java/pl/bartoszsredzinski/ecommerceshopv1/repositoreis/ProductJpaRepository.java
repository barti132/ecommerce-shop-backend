package pl.bartoszsredzinski.ecommerceshopv1.repositoreis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;

import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByNameContainsIgnoreCase(String name);

    List<Product> findAllByNameContainsIgnoreCaseAndCategoryContainsIgnoreCase(String name, String category);
}
