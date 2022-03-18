package pl.bartoszsredzinski.ecommerceshopv1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class ProductServiceTest{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @BeforeEach
    void init(){
        Product product1 = Product.builder()
                .category("category1")
                .producerName("producer1")
                .name("Special product")
                .description("")
                .img("")
                .priceNet(new BigDecimal(0))
                .priceGross(new BigDecimal(5))
                .available(true)
                .build();
        Product product2 = Product.builder()
                .category("category2")
                .producerName("producer2")
                .name("Special product but another")
                .description("")
                .img("")
                .priceNet(new BigDecimal(0))
                .priceGross(new BigDecimal(10))
                .available(true)
                .build();
        Product product3 = Product.builder()
                .category("category3")
                .producerName("producer3")
                .name("name3 o")
                .description("")
                .img("")
                .priceNet(new BigDecimal(0))
                .priceGross(new BigDecimal(15))
                .available(true)
                .build();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }

    @Test
    public void getProducersByCategory_should_work(){
        List<String> results = productRepository.getProducersByCategory("category2");
        assertEquals(1, results.size());
        assertEquals("producer2", results.get(0));
    }

    @Test
    public void getRandom_should_throw_exception(){
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> productService.getRandomProducts(4));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> productService.getRandomProducts(0));
    }

    @Test
    public void getRandom_should_work(){
        assertEquals(2, productService.getRandomProducts(2).size());
    }

    @Test
    public void findProductByCriteria_should_work(){
        List<Product> products = productService.findProductByCriteria("name3", null, null, null, null);
        assertEquals("name3 o", products.get(0).getName());

        products = productService.findProductByCriteria(null, "category3", null, null, null);
        assertEquals("category3", products.get(0).getCategory());

        products = productService.findProductByCriteria("o", null, "producer2", null, null);
        assertEquals(1, products.size());

        products = productService.findProductByCriteria("o", null, null, "10", "20");
        assertEquals(2, products.size());
        products = productService.findProductByCriteria("o", null, null, "15", "20");
        assertEquals(1, products.size());
        products = productService.findProductByCriteria("o", null, null, "11", null);
        assertEquals(1, products.size());
        products = productService.findProductByCriteria("o", null, null, null, "10");
        assertEquals(2, products.size());

        products = productService.findProductByCriteria("name3", "category3", "producer3", "15", "20");
        assertEquals(1, products.size());
    }

    @Test
    public void findById_should_return_product(){
        assertEquals("name3 o", productService.findById(3L).getName());
        assertNotNull(productService.findById(1L));
    }

    @Test
    public void findById_should_return_null(){
        assertNull(productService.findById(-1L));
        assertNull(productService.findById(4L));
    }
}