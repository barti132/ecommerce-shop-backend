package pl.bartoszsredzinski.ecommerceshopv1.services;

import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.ecommerceshopv1.model.Category;
import pl.bartoszsredzinski.ecommerceshopv1.repositoreis.CategoryCrudRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CrudService<Category, Integer>{

    private final CategoryCrudRepository repository;

    public CategoryService(CategoryCrudRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Category> findAll(){
        List<Category> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public Category findByName(String name){
        return repository.findByName(name);
    }

    @Override
    public Category findById(Integer id){
        return repository.findById((long)id).orElse(null);
    }

    @Override
    public Category save(Category object){
        if(repository.findByName(object.getName()) == null){
            repository.save(object);
            return object;
        }
        return null;
    }

    @Override
    public void delete(Category object){
        repository.delete(object);
    }

    @Override
    public void deleteById(Integer id){
        repository.deleteById((long)id);
    }
}
