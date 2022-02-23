package pl.bartoszsredzinski.ecommerceshopv1.service;

import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.repository.ProductJpaRepository;

import java.util.*;

/**
 * Service - working on ProductJpaRepository
 * Implements interface CrudService with Product as repository class & Integer as id
 *
 * @author Bartosz Średziński
 * @see pl.bartoszsredzinski.ecommerceshopv1.service.CrudService
 */
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

    public List<Product> findByCriteria(String name, String category){
        if(category.equals("any")){
            return repository.findAllByNameContainsIgnoreCase(name);
        }
        else{
            return repository.findAllByNameContainsIgnoreCaseAndCategoryContainsIgnoreCase(name, category);
        }
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

    public List<Product> getProductsByCategory(String category){
        return repository.findAllByCategory(category);
    }

    public List<String> getProducersByCategory(String category){
        return repository.getProducersByCategory(category);
    }
}
