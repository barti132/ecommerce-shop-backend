package pl.bartoszsredzinski.ecommerceshopv1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.Invoice;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 06.03.2022
 */
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long>{
}