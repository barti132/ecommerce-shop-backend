package pl.bartoszsredzinski.ecommerceshopv1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.bartoszsredzinski.ecommerceshopv1.dto.InvoiceDto;
import pl.bartoszsredzinski.ecommerceshopv1.model.Invoice;

/**
 * Invoice mapper
 *
 * @author Bartosz Średziński
 * created on 15.03.2022
 */
@Mapper(componentModel = "spring")
public interface InvoiceMapper{
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    InvoiceDto invoiceToInvoiceDto(Invoice invoice);
}
