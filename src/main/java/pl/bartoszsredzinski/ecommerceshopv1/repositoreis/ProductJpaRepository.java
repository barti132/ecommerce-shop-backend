package pl.bartoszsredzinski.ecommerceshopv1.repositoreis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>{
}
