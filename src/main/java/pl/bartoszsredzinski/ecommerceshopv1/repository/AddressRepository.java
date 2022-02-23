package pl.bartoszsredzinski.ecommerceshopv1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.Address;

/**
 * Address Crud Repository
 *
 * @author Bartosz Średziński
 * created on 23.02.2022
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>{
}
