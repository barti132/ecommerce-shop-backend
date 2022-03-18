package pl.bartoszsredzinski.ecommerceshopv1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Stock request
 *
 * @author Bartosz Średziński
 * created on 18.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockRequest{
    @NotNull
    private Long id;

    @NotNull
    private Integer amount;
}
