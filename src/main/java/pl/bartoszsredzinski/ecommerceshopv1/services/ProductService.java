package pl.bartoszsredzinski.ecommerceshopv1.services;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.repositoreis.ProductJpaRepository;
import pl.bartoszsredzinski.ecommerceshopv1.specifications.ProductSpecification;
import pl.bartoszsredzinski.ecommerceshopv1.specifications.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductService implements CrudService<Product, Integer>{

    private final ProductJpaRepository repository;

    public ProductService(ProductJpaRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Product> findAll(){
        return repository.findAll();
    }

    @Override
    public Product findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product object){
        return repository.save(object);
    }

    @Override
    public void delete(Product object){
        repository.delete(object);
    }

    @Override
    public void deleteById(Integer id){
        repository.deleteById(id);
    }

    public List<Product> findByCriteria(String name, String category, String subcategory){
        ProductSpecification spec1 = new ProductSpecification(new SearchCriteria("name", ":", name));
        ProductSpecification spec2 = new ProductSpecification(new SearchCriteria("category", ":", category));
        ProductSpecification spec3 = new ProductSpecification(new SearchCriteria("sub_category", ":", subcategory));
        return repository.findAll(Specification.where(spec1).and(spec2).and(spec3));
    }
}
