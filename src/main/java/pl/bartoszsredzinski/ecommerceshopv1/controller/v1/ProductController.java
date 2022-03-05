package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.service.ProductService;

import java.util.List;
import java.util.Objects;

/**
 * Product rest controller
 *
 * @author Bartosz Średziński
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/products/")
public class ProductController{

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id){
        log.info("GET products/" + id);
        return productService.findById(id);
    }

    @GetMapping("search")
    public List<Product> findProduct(@RequestParam(required = false) String name, @RequestParam(required = false) String category,
                                     @RequestParam(required = false) String producer, @RequestParam(required = false) String lowerPrice,
                                     @RequestParam(required = false) String upperPrice){

        log.info("GET products/search?name=" + name + "&category=" + category + "&producer=" + producer + "&lowerPrice=" + lowerPrice + "&upperPrice=" + upperPrice);
        return productService.findProductByCriteria(name, category, producer, lowerPrice, upperPrice);
    }

    @GetMapping("random")
    public List<Product> getRandomProducts(@RequestParam(required = false) Integer limit){
        log.info("GET products/random?limit=" + limit);
        return productService.getRandomProducts(Objects.requireNonNullElse(limit, 6));
    }

    @GetMapping("{category}/producers")
    public List<String> getProducersByCategory(@PathVariable String category){
        log.info("GET products/" + category + "/producers");
        return productService.getProducersByCategory(category);
    }
}
