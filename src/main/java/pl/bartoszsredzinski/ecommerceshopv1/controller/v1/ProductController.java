package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.service.ProductService;

import java.util.List;
import java.util.Objects;

/**
 * This controller class handle request with products.
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


    /**
     * GET /api/v1/products/{id}
     * <p>
     * This endpoint return a product with given id
     * </p>
     *
     * @param id Integer - product id
     * @return Product or null
     */
    @GetMapping("{id}")
    public Product getProductById(@PathVariable Integer id){
        log.info("GET products/" + id);
        return productService.findById(id);
    }

    /**
     * GET /api/v1/products/search
     * OPTIONAL parameters ?name=""&category=""
     * <p>
     * This endpoint is searching for a products with given criteria
     * </p>
     *
     * @param name     String - searching product name
     * @param category String - searching product category
     * @return List<Product> with 200 code
     */
    @GetMapping("search")
    public List<Product> findProduct(@RequestParam(required = false) String name, @RequestParam(required = false) String category){

        log.info("GET products/search?name=" + name + "&category=" + category);

        if(name != null){
            return productService.findByCriteria(name, category);
        }

        return productService.findAll();
    }

    /**
     * GET /api/v1/products/random
     * OPTIONAL ?limit=
     *
     * <p>
     * Endpoints return list of random products. Default size of list is 6.
     * </p>
     *
     * @param limit Integer size of list
     * @return List<Product> list of random products
     */
    @GetMapping("random")
    public List<Product> getRandomProducts(@RequestParam(required = false) Integer limit){
        log.info("GET products/random?limit=" + limit);

        try{
            return productService.getRandomProducts(Objects.requireNonNullElse(limit, 6));
        }catch(ArrayIndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * GET /api/v1/products/category/{category}
     *
     * <p>
     *     Endpoint return list of products with given category.
     * </p>
     *
     * @param category String category name
     * @return List<Product> list of products with given category
     */
    @GetMapping("category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category){
        log.info("GET products/category/" + category);

        return productService.getProductsByCategory(category);
    }

    @GetMapping("{category}/producers")
    public List<String> getProducersByCategory(@PathVariable String category){
        log.info("GET products/" + category + "/producers");
        return productService.getProducersByCategory(category);
    }
}
