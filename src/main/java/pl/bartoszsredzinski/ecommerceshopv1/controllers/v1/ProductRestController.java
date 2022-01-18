package pl.bartoszsredzinski.ecommerceshopv1.controllers.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/")
public class ProductRestController{

    private final ProductService productService;

    public ProductRestController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.findById(id);
    }

    @GetMapping("all")
    public List<Product> getAllProduct(){
        return productService.findAll();
    }
}
