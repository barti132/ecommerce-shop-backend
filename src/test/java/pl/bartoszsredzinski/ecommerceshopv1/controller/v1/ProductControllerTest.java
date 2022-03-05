package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.model.Stock;
import pl.bartoszsredzinski.ecommerceshopv1.service.ProductService;
import pl.bartoszsredzinski.ecommerceshopv1.service.StockService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Product controller test
 *
 * @author Bartosz Średziński
 * created on 04.03.2022
 */
class ProductControllerTest{

    private final Product product = Product.builder().id(1L).category("category1").producerName("producer1").name("name").description("").img("").priceNet(new BigDecimal(0)).priceGross(new BigDecimal(5)).build();
    private final ProductService productService = Mockito.mock(ProductService.class);
    private final StockService stockService = Mockito.mock(StockService.class);
    private final ProductController productController = new ProductController(productService, stockService);

    @Test
    public void productGetById_should_return_product(){

        when(productService.findById(1L)).thenReturn(product);

        assertEquals(product, productController.getProductById(1L));
    }

    @Test
    public void findProduct_should_return_product(){
        when(productService.findProductByCriteria("name", "category", null, null, null))
                .thenReturn(List.of(product));

        assertEquals(product, productController.findProduct("name", "category", null, null, null).get(0));
    }

    @Test
    public void findProduct_should_return_empty_array(){
        when(productService.findProductByCriteria("name", "category", null, null, null))
                .thenReturn(new ArrayList<>());
        assertEquals(0, productController.findProduct("name", "category", null, null, null).size());
    }

    @Test
    public void getRandomProducts_with_null_should_return_6_length_array(){
        when(productService.getRandomProducts(6))
                .thenReturn(Arrays.asList(new Product(), new Product(), new Product(), new Product(), new Product(), new Product()));

        assertEquals(6, productController.getRandomProducts(null).size());
    }

    @Test
    public void getRandomProducts_with_limit_should_return_array(){
        when(productService.getRandomProducts(3))
                .thenReturn(Arrays.asList(new Product(), new Product(), new Product()));

        assertEquals(3, productController.getRandomProducts(3).size());
    }

    @Test
    public void getProducersByCategory_should_return_producerName(){
        when(productService.getProducersByCategory("category"))
                .thenReturn(List.of("producer1"));

        assertEquals("producer1", productController.getProducersByCategory("category").get(0));
    }
}