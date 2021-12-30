package pl.bartoszsredzinski.ecommerceshopv1.services;

import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.ecommerceshopv1.model.SubCategory;
import pl.bartoszsredzinski.ecommerceshopv1.repositoreis.SubCategoryCrudRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubCategoryService implements CrudService<SubCategory, Integer>{

    private final SubCategoryCrudRepository repository;

    public SubCategoryService(SubCategoryCrudRepository repository){
        this.repository = repository;
    }

    @Override
    public List<SubCategory> findAll(){
        List<SubCategory> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public SubCategory findByName(String name){
        return repository.findByName(name);
    }

    @Override
    public SubCategory findById(Integer id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public SubCategory save(SubCategory object){
        return repository.save(object);
    }

    @Override
    public void delete(SubCategory object){
        repository.delete(object);
    }

    @Override
    public void deleteById(Integer id){
        repository.deleteById(id);
    }
}
