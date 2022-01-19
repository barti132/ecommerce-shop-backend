package pl.bartoszsredzinski.ecommerceshopv1.controllers.v1;

import org.springframework.web.bind.annotation.*;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ProductRestController{

    private final ProductService productService;

    public ProductRestController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("product/{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.findById(id);
    }

    @GetMapping("products")
    public List<Product> findProduct(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("subcategory") String subcategory){
        return productService.findByCriteria(name, category, subcategory);
    }
}
