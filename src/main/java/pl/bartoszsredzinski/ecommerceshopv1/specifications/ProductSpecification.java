package pl.bartoszsredzinski.ecommerceshopv1.specifications;

import org.springframework.data.jpa.domain.Specification;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification implements Specification<Product>{

    private SearchCriteria criteria;

    public ProductSpecification(SearchCriteria criteria){
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder){
        if(criteria.getOperation().equalsIgnoreCase(">")){
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if(criteria.getOperation().equalsIgnoreCase("<")){
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if(criteria.getOperation().equalsIgnoreCase(":")){
            if(root.get(criteria.getKey()).getJavaType() == String.class){
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            }
            else{
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
