package pl.bartoszsredzinski.ecommerceshopv1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import pl.bartoszsredzinski.ecommerceshopv1.dto.request.StockRequest;
import pl.bartoszsredzinski.ecommerceshopv1.exception.InvalidIdException;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.model.Stock;
import pl.bartoszsredzinski.ecommerceshopv1.repository.ProductRepository;
import pl.bartoszsredzinski.ecommerceshopv1.repository.StockRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bartosz Średziński
 * created on 17.03.2022
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class StockServiceTest{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    private Product product1;

    @BeforeEach
    void init(){
        product1 = Product.builder()
                .category("category1")
                .producerName("producer1")
                .name("Special product")
                .description("")
                .img("")
                .priceNet(new BigDecimal(0))
                .priceGross(new BigDecimal(5))
                .available(true)
                .build();

        productRepository.save(product1);

        Product product2 = Product.builder()
                .category("category1")
                .producerName("producer1")
                .name("Special product")
                .description("")
                .img("")
                .priceNet(new BigDecimal(0))
                .priceGross(new BigDecimal(5))
                .available(true)
                .build();
        productRepository.save(product2);

        Stock s1 = new Stock(1L, product1, 50, new Date(System.currentTimeMillis()));
        Stock s2 = new Stock(2L, product2, 25, new Date(System.currentTimeMillis()));
        stockRepository.save(s1);
        stockRepository.save(s2);
    }

    @Test
    public void findByProductId_should_return_stock(){
        assertNotNull(stockService.findByProductId(1L));
    }

    @Test
    public void findByProductId_should_return_exception(){
        assertThrows(InvalidIdException.class, () -> stockService.findByProductId(3L));
        assertThrows(InvalidIdException.class, () -> stockService.findByProductId(0L));
    }

    @Test
    public void getWholeStack_should_return_2_stocks_as_list(){
        assertEquals(2, stockService.getWholeStock().size());
    }

    @Test
    public void updateStock_should_update_stock_amount(){
        stockService.updateStock(new StockRequest(1L, 1));
        assertEquals(1, stockRepository.findById(1L).get().getAmount());
    }

    @Test
    public void updateStock_should_set_unavailable_product_and_delete_stock(){
        stockService.deleteStock(1L);
        assertEquals(false, productRepository.findById(1L).get().getAvailable());
        assertEquals(1, ((List<Stock>) stockRepository.findAll()).size());
    }
}