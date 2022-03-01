package pl.bartoszsredzinski.ecommerceshopv1.service.productspecification;

import org.springframework.data.jpa.domain.Specification;
import pl.bartoszsredzinski.ecommerceshopv1.model.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Product specifiation
 *
 * @author Bartosz Średziński
 * created on 01.03.2022
 */
public class ProductSpecification implements Specification<Product>{

    private List<SearchCriteria> searchCriteriaList;

    public ProductSpecification(){
        searchCriteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria criteria){
        searchCriteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder){

        List<Predicate> predicates = new ArrayList<>();

        for(SearchCriteria criteria : searchCriteriaList){
            switch(criteria.getOperation()){
                case ":":
                    predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case ">=":
                    predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), new BigDecimal(criteria.getValue())));
                    break;
                case "<=":
                    predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), new BigDecimal(criteria.getValue())));
                    break;
                case "=":
                    predicates.add(builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
                    break;
                default:
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
