package pl.bartoszsredzinski.ecommerceshopv1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartoszsredzinski.ecommerceshopv1.dto.request.ProductRequest;
import pl.bartoszsredzinski.ecommerceshopv1.exception.InvalidIdException;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;
import pl.bartoszsredzinski.ecommerceshopv1.model.Stock;
import pl.bartoszsredzinski.ecommerceshopv1.repository.ProductRepository;
import pl.bartoszsredzinski.ecommerceshopv1.repository.StockRepository;
import pl.bartoszsredzinski.ecommerceshopv1.service.productspecification.ProductSpecification;
import pl.bartoszsredzinski.ecommerceshopv1.service.productspecification.SearchCriteria;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

/**
 * Product service
 *
 * @author Bartosz Średziński
 */
@Service
public class ProductService{

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    public ProductService(ProductRepository productRepository, StockRepository stockRepository){
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    public List<Product> getRandomProducts(Integer limit){
        int productsNumber = (int) productRepository.count();

        if(productsNumber < limit || limit < 1){
            throw new ArrayIndexOutOfBoundsException("Limit wrong size.");
        }

        Random random = new Random();
        Set<Product> randomProducts = new LinkedHashSet<>();

        while(randomProducts.size() != limit){
            randomProducts.add(productRepository.findById(random.nextLong(productsNumber - 1) + 1).orElse(null));
        }

        return new ArrayList<>(randomProducts);
    }

    public List<String> getProducersByCategory(String category){
        return productRepository.getProducersByCategory(category);
    }

    public List<Product> findProductByCriteria(String name, String category, String producer, String lowerPrice, String upperPrice){
        if(name == null && category == null){
            return productRepository.findAll();
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
        }
        if(upperPrice != null){
            specification.add(new SearchCriteria("priceGross", "<=", upperPrice));
        }


        return productRepository.findAll(specification);
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public void createNewProduct(ProductRequest productRequest){
        Product product = new Product();
        product = productRepository.save(setProductProperties(product, productRequest));

        Stock stock = new Stock();
        stock.setAmount(0);
        stock.setProduct(product);
        stock.setUpdatedDate(new Date(System.currentTimeMillis()));

        stockRepository.save(stock);
    }

    @Transactional
    public void updateProduct(Long id, ProductRequest productRequest){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new InvalidIdException("Product " + id + " not found"));
        product = setProductProperties(product, productRequest);
        productRepository.save(product);
    }

    private Product setProductProperties(Product product, ProductRequest productRequest){
        product.setName(productRequest.getName());
        product.setProducerName(productRequest.getProducerName());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());

        product.setPriceNet(productRequest.getPriceNet());

        BigDecimal priceGross = productRequest.getPriceNet().multiply(new BigDecimal("0.23"));
        priceGross = priceGross.add(productRequest.getPriceNet());

        product.setPriceGross(priceGross);
        product.setAvailable(true);
        if(productRequest.getImgName().contains("api/v1")){
            product.setImg(productRequest.getImgName());
        }
        else{
            product.setImg("http://localhost:8080/api/v1/image/" + productRequest.getImgName());
        }
        return product;
    }
}
