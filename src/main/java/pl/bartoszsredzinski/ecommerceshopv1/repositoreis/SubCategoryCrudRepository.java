package pl.bartoszsredzinski.ecommerceshopv1.repositoreis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.SubCategory;

@Repository
public interface SubCategoryCrudRepository extends CrudRepository<SubCategory, Integer>{
    SubCategory findByName(String name);
}
