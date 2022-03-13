package pl.bartoszsredzinski.ecommerceshopv1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Cart item request model
 *
 * @author Bartosz Średziński
 * created on 03.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest{
    @NotNull
    private Long productId;

    @NotNull
    @Min(1)
    private Integer amount;
}
