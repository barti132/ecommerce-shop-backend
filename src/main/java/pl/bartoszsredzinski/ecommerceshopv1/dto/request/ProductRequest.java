package pl.bartoszsredzinski.ecommerceshopv1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Product request
 *
 * @author Bartosz Średziński
 * created on 19.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest{

    @NotBlank
    private String category;

    @NotBlank
    private String producerName;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal priceNet;

    @NotBlank
    private String description;

    @NotBlank
    private String imgName;
}
