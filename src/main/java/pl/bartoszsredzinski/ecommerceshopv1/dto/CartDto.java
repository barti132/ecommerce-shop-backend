package pl.bartoszsredzinski.ecommerceshopv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartoszsredzinski.ecommerceshopv1.model.CartItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 03.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto{
    private BigDecimal totalPriceGross;
    private BigDecimal totalPriceNet;
    private Integer totalItems;
    private List<CartItem> products;
}
