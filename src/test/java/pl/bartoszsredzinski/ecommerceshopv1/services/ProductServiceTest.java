package pl.bartoszsredzinski.ecommerceshopv1.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;

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
        product1 = new Product();
        product1.setCategory("category1");
        product1.setProducer_name("producer1");
        product1.setName("Special product");
        product1.setDescription("");
        product1.setImg("");

        product2 = new Product();
        product2.setCategory("category2");
        product2.setProducer_name("producer2");
        product2.setName("Special product but another");
        product2.setDescription("");
        product2.setImg("");

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
        assertEquals(list.size(), 2);

        list = productService.findByCriteria("", "any");
        assertEquals(list.size(), 2);

        list = productService.findByCriteria("another", "any");
        assertEquals(list.size(), 1);

        list = productService.findByCriteria("", "category1");
        assertEquals(list.size(), 1);
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
        List<Product>list = productService.getRandomProducts(2);
        assertEquals(list.size(), 2);
    }

    @Test
    @Order(7)
    public void delete_should_work(){
        productService.delete(product1);
        assertEquals(1, productService.findAll().size());
    }

    @Test
    @Order(8)
    public void deleteById_should_work(){
        productService.deleteById(2);
        assertEquals(0, productService.findAll().size());
    }
}