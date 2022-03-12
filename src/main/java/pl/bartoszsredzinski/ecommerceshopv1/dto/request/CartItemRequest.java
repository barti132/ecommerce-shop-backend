package pl.bartoszsredzinski.ecommerceshopv1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 03.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest{
    private Long productId;
    private Integer amount;
}
