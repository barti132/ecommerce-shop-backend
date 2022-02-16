package pl.bartoszsredzinski.ecommerceshopv1.controllers.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.services.ProductService;

import java.util.List;

/**
 * This controller class handle request with products.
 *
 * @author Bartosz Średziński
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class ProductRestController{

    private final ProductService productService;

    public ProductRestController(ProductService productService){
        this.productService = productService;
    }


    /**
     * GET /api/v1/product/{id}
     * <p>
     *  This endpoint return a product with given id
     * </p>
     *
     * @param id Integer - product id
     *
     * @return Product or null  with code 200
     */
    @GetMapping("product/{id}")
    public Product getProductById(@PathVariable Integer id){
        log.info("GET product/" + id);
        return productService.findById(id);
    }

    /**
     * GET /api/v1/products?name=""&category=""
     * <p>
     *  This endpoint is searching for a products with given criteria
     * </p>
     *
     * @param name String - searching product name
     * @param category String - searching product category
     *
     * @return List<Product> with 200 code
     */
    @GetMapping("products")
    public List<Product> findProduct(
            @RequestParam("name") String name,
            @RequestParam("category") String category){
        log.info("GET products?name=" + name + "&category=" + category);
        return productService.findByCriteria(name, category);
    }
}
