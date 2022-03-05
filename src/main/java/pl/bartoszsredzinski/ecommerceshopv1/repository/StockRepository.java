package pl.bartoszsredzinski.ecommerceshopv1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.model.Stock;

import java.util.Optional;

/**
 * Stock crud repository
 *
 * @author Bartosz Średziński
 * created on 05.03.2022
 */
@Repository
public interface StockRepository extends CrudRepository<Stock, Long>{
    Optional<Stock> findByProduct(Product product);
}
