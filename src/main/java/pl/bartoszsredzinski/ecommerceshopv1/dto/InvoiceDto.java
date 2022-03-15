package pl.bartoszsredzinski.ecommerceshopv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartoszsredzinski.ecommerceshopv1.model.Address;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Invoice data transfer object
 *
 * @author Bartosz Średziński
 * created on 15.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto{
    private Long id;

    private Date InvoiceDate;

    private Address address;

    private BigDecimal totalPriceGross;

    private BigDecimal totalPriceNet;

    private Integer totalItems;

    private String cartNumber;
}
