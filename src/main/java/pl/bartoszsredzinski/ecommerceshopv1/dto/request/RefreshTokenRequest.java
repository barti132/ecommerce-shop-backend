package pl.bartoszsredzinski.ecommerceshopv1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 26.02.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest{

    @NotBlank
    private String refreshToken;

    @NotNull
    @NotBlank(message = "Login is mandatory")
    private String login;
}
