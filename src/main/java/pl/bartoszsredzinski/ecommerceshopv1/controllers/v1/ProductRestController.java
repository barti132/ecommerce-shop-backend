package pl.bartoszsredzinski.ecommerceshopv1.controllers.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.model.SubCategory;
import pl.bartoszsredzinski.ecommerceshopv1.services.CategoryService;
import pl.bartoszsredzinski.ecommerceshopv1.services.ProductService;
import pl.bartoszsredzinski.ecommerceshopv1.services.SubCategoryService;

@RestController
@RequestMapping("/api/v1/product/")
public class ProductRestController{

    private final ProductService productService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    public ProductRestController(ProductService productService, CategoryService categoryService, SubCategoryService subCategoryService){
        this.productService = productService;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Integer id){
        return productService.findById(id);
    }

}
