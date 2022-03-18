package pl.bartoszsredzinski.ecommerceshopv1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartoszsredzinski.ecommerceshopv1.dto.request.StockRequest;
import pl.bartoszsredzinski.ecommerceshopv1.exception.InvalidIdException;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.model.Stock;
import pl.bartoszsredzinski.ecommerceshopv1.repository.ProductRepository;
import pl.bartoszsredzinski.ecommerceshopv1.repository.StockRepository;

import java.sql.Date;
import java.util.List;

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
        return stockRepository.findByProduct(productRepository.getById(id))
                .orElseThrow(() -> new InvalidIdException("Product " + id + " not found"));
    }

    public List<Stock> getWholeStock(){
        return (List<Stock>) stockRepository.findAll();
    }

    @Transactional
    public void updateStock(StockRequest stockRequest){
        Stock stock = stockRepository.findById(stockRequest.getId())
                .orElseThrow(() -> new InvalidIdException("Stock " + stockRequest.getId() + " not found"));
        stock.setUpdatedDate(new Date(System.currentTimeMillis()));
        stock.setAmount(stockRequest.getAmount());
        stockRepository.save(stock);
    }

    @Transactional
    public void deleteStock(Long id){
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new InvalidIdException("Stock " + id + " not found"));
        Product product = productRepository.findById(stock.getProduct().getId())
                .orElseThrow(() -> new InvalidIdException("Product " + stock.getProduct().getId() + " not found"));
        product.setAvailable(false);
        productRepository.save(product);
        stockRepository.deleteById(id);
    }
}
