package pl.bartoszsredzinski.ecommerceshopv1.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void init() {
        product1 = Product.builder()
                .category("category1")
                .producerName("producer1")
                .name("Special product")
                .description("")
                .img("")
                .priceNet(new BigDecimal(0))
                .priceGross(new BigDecimal(5)).build();

        product2 = Product.builder()
                .category("category2")
                .producerName("producer2")
                .name("Special product but another")
                .description("")
                .img("")
                .priceNet(new BigDecimal(0))
                .priceGross(new BigDecimal(10)).build();

        product3 = Product.builder()
                .category("category3")
                .producerName("producer3")
                .name("name3 o")
                .description("")
                .img("")
                .priceNet(new BigDecimal(0))
                .priceGross(new BigDecimal(15)).build();

        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
    }

    @Test
    public void getProducersByCategory_should_work(){
        List<String> results = productService.getProducersByCategory("category2");
        assertEquals(1, results.size());
        assertEquals("producer2", results.get(0));
    }

    @Test
    public void findAll_should_work(){
        List<Product> findResult = productService.findAll();
        assertEquals(3, findResult.size());
        assertEquals(findResult.get(0).getName(), product1.getName());
        assertEquals(findResult.get(1).getName(), product2.getName());
    }

    @Test
    public void findById_should_work(){
        //sql counting from 1
        Product prod1 = productService.findById(1L);
        assertEquals(prod1.getName(), product1.getName());
        Product prod2 = productService.findById(2L);
        assertEquals(prod2.getName(), product2.getName());
    }

    @Test
    public void findById_should_be_null(){
        Product prod1 = productService.findById(0L);
        assertNull(prod1);
        Product prod2 = productService.findById(4L);
        assertNull(prod2);
    }

    @Test
    public void getRandom_should_throw_exception(){
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> productService.getRandomProducts(4));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> productService.getRandomProducts(0));
    }

    @Test
    public void getRandom_should_work(){
        assertEquals(2, productService.getRandomProducts(2).size());
    }

    @Test
    public void delete_should_work(){
        productService.delete(product1);
        assertEquals(2, productService.findAll().size());
    }

    @Test
    public void deleteById_should_work(){
        Long id = productService.findAll().get(0).getId();
        productService.deleteById(id);
        assertEquals(2, productService.findAll().size());
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
}