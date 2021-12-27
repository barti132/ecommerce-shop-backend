package pl.bartoszsredzinski.ecommerceshopv1.repositoreis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.Category;

@Repository
public interface CategoryCrudRepository extends CrudRepository<Category, Long>{
}
