package pl.bartoszsredzinski.ecommerceshopv1.service;

import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.repository.ProductRepository;
import pl.bartoszsredzinski.ecommerceshopv1.service.productspecification.ProductSpecification;
import pl.bartoszsredzinski.ecommerceshopv1.service.productspecification.SearchCriteria;

import java.math.BigDecimal;
import java.util.*;

/**
 * Product service
 *
 * @author Bartosz Średziński
 * @see pl.bartoszsredzinski.ecommerceshopv1.service.CrudService
 */
@Service
public class ProductService implements CrudService<Product, Integer>{

    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
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

    public List<Product> getRandomProducts(Integer limit) throws ArrayIndexOutOfBoundsException{
        int productsNumber = (int) repository.count();

        if(productsNumber < limit || limit < 1){
            throw new ArrayIndexOutOfBoundsException("Limit wrong size.");
        }

        Random random = new Random();
        Set<Product> randomProducts = new LinkedHashSet<>();

        while(randomProducts.size() != limit){
            randomProducts.add(findById(random.nextInt(productsNumber - 1) + 1));
        }

        return new ArrayList<>(randomProducts);
    }

    public List<String> getProducersByCategory(String category){
        return repository.getProducersByCategory(category);
    }

    public List<Product> findProductByCriteria(String name, String category, String producer, String lowerPrice, String upperPrice){
        if(name == null && category == null){
            return findAll();
        }

        ProductSpecification specification = new ProductSpecification();
        if(name != null){
            specification.add(new SearchCriteria("name", "=", name));
        }
        if(category != null){
            specification.add(new SearchCriteria("category", "=", category));
        }
        if(producer != null){
            specification.add(new SearchCriteria("producerName", ":", producer));
        }
        if(lowerPrice != null){
            specification.add(new SearchCriteria("priceGross", ">=", lowerPrice));
            specification.add(new SearchCriteria("priceGross", "<=", upperPrice));
        }


        return repository.findAll(specification);
    }
}
