package pl.bartoszsredzinski.ecommerceshopv1.service;

import java.util.List;

/**
 * Interface with basic crud methods
 *
 * @param <T> - model class
 * @param <ID> - Id class
 */
public interface CrudService<T, ID>{
    List<T> findAll();
    T findById(ID id);
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
}
