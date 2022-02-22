package pl.bartoszsredzinski.ecommerceshopv1.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeAll
    void init() {
        product1 = Product.builder()
                .category("category1")
                .producer_name("producer1")
                .name("Special product")
                .description("")
                .img("")
                .price_net(new BigDecimal(0))
                .price_gross(new BigDecimal(0)).build();

        product2 = Product.builder()
                .category("category2")
                .producer_name("producer2")
                .name("Special product but another")
                .description("")
                .img("")
                .price_net(new BigDecimal(0))
                .price_gross(new BigDecimal(0)).build();

        productService.save(product1);
        productService.save(product2);
    }

    @Test
    @Order(1)
    public void findAll_should_work(){
        List<Product> findResult = productService.findAll();
        assertEquals(2, findResult.size());
        assertEquals(findResult.get(0).getName(), product1.getName());
        assertEquals(findResult.get(1).getName(), product2.getName());
    }

    @Test
    @Order(2)
    public void findById_should_work(){
        //sql counting from 1
        Product prod1 = productService.findById(1);
        assertEquals(prod1.getName(), product1.getName());
        Product prod2 = productService.findById(2);
        assertEquals(prod2.getName(), product2.getName());
    }

    @Test
    @Order(3)
    public void findById_should_be_null(){
        Product prod1 = productService.findById(0);
        assertNull(prod1);
        Product prod2 = productService.findById(3);
        assertNull(prod2);
    }

    @Test
    @Order(4)
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
    @Order(5)
    public void getRandom_should_throw_exception(){
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> productService.getRandomProducts(3));
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> productService.getRandomProducts(0));
    }

    @Test
    @Order(6)
    public void getRandom_should_work(){
        assertEquals(2, productService.getRandomProducts(2).size());
    }

    @Test
    @Order(7)
    public void getByCategory_should_work(){
        assertEquals(1, productService.getProductsByCategory("category1").size());
        assertEquals(1, productService.getProductsByCategory("category2").size());
        assertEquals(0, productService.getProductsByCategory("category3").size());
    }

    @Test
    @Order(8)
    public void delete_should_work(){
        productService.delete(product1);
        assertEquals(1, productService.findAll().size());
    }

    @Test
    @Order(9)
    public void deleteById_should_work(){
        productService.deleteById(2);
        assertEquals(0, productService.findAll().size());
    }
}