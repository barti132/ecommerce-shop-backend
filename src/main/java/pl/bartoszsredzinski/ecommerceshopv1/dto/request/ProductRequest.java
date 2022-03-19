package pl.bartoszsredzinski.ecommerceshopv1.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Product request
 *
 * @author Bartosz Średziński
 * created on 19.03.2022
 */
public class ProductRequest{

    @NotBlank
    private String category;

    @NotBlank
    private String producerName;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal priceGross;

    @NotBlank
    private String description;

    @NotBlank
    private String imgName;
}
