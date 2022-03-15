package pl.bartoszsredzinski.ecommerceshopv1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Order request model
 *
 * @author Bartosz Średziński
 * created on 05.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest{

    @NotNull
    private Long addressId;

    @NotBlank(message = "Card number is mandatory")
    @Size(min = 15, max = 16)
    private String cardNumber;

    @NotBlank(message = "Name on the card is mandatory")
    private String cardName;

    @NotBlank(message = "Expriation is mandatory")
    @Pattern(regexp = "([0-9]{2}[/]?){2}")
    private String expiration;

    @NotBlank(message = "Security code is mandatory")
    @Size(min = 3, max = 4)
    private String securityCode;
}
