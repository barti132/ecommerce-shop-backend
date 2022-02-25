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

    @BeforeEach
    void init() {
        product1 = Product.builder()
                .category("category1")
                .producerName("producer1")
                .name("Special product")
                .description("")
                .img("")
                .priceNet(new BigDecimal(0))
                .priceGross(new BigDecimal(0)).build();

        product2 = Product.builder()
                .category("category2")
                .producerName("producer2")
                .name("Special product but another")
                .description("")
                .img("")
                .priceNet(new BigDecimal(0))
                .priceGross(new BigDecimal(0)).build();

        productService.save(product1);
        productService.save(product2);
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
        assertEquals(2, findResult.size());
        assertEquals(findResult.get(0).getName(), product1.getName());
        assertEquals(findResult.get(1).getName(), product2.getName());
    }

    @Test
    public void findById_should_work(){
        //sql counting from 1
        Product prod1 = productService.findById(1);
        assertEquals(prod1.getName(), product1.getName());
        Product prod2 = productService.findById(2);
        assertEquals(prod2.getName(), product2.getName());
    }

    @Test
    public void findById_should_be_null(){
        Product prod1 = productService.findById(0);
        assertNull(prod1);
        Product prod2 = productService.findById(3);
        assertNull(prod2);
    }

    @Test
    public void findByCriteria_should_work(){
        List<Product> list = productService.findByCriteria("Special", "any");
        assertEquals(2, list.size());

        list = productService.findByCriteria("", "any");
        assertEquals(2, list.size());

        list = productService.findByCriteria("another", "any");
        assertEquals(1, list.size());

        list = productService.findByCriteria("", "category1");
        assertEquals(1, list.size());
    }

    @Test
    public void getRandom_should_throw_exception(){
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> productService.getRandomProducts(3));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> productService.getRandomProducts(0));
    }

    @Test
    public void getRandom_should_work(){
        assertEquals(2, productService.getRandomProducts(2).size());
    }

    @Test
    public void getByCategory_should_work(){
        assertEquals(1, productService.getProductsByCategory("category1").size());
        assertEquals(1, productService.getProductsByCategory("category2").size());
        assertEquals(0, productService.getProductsByCategory("category3").size());
    }

    @Test
    public void delete_should_work(){
        productService.delete(product1);
        assertEquals(1, productService.findAll().size());
    }

    @Test
    public void deleteById_should_work(){
        Integer id = productService.findAll().get(0).getId();
        productService.deleteById(id);
        assertEquals(1, productService.findAll().size());
    }
}