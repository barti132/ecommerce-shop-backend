package pl.bartoszsredzinski.ecommerceshopv1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.model.Stock;
import pl.bartoszsredzinski.ecommerceshopv1.repository.ProductRepository;
import pl.bartoszsredzinski.ecommerceshopv1.repository.StockRepository;

/**
 * Stock service
 *
 * @author Bartosz Średziński
 * created on 05.03.2022
 */
@Service
@AllArgsConstructor
public class StockService{

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    public Stock findByProductId(Long id){
        return stockRepository.findByProduct(productRepository.getById(id)).orElse(null);
    }
}
