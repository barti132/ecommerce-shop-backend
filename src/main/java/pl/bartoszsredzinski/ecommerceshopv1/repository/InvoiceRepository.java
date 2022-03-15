package pl.bartoszsredzinski.ecommerceshopv1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.bartoszsredzinski.ecommerceshopv1.model.Invoice;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Invoice repository
 *
 * @author Bartosz Średziński
 * created on 06.03.2022
 */
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

    @Query("SELECT invoice FROM Invoice invoice JOIN FETCH invoice.products WHERE invoice.id = :id")
    Optional<Invoice> getFullInvoiceByID(@Param("id") Long id);

    List<Invoice> findByUser(User user);
}
