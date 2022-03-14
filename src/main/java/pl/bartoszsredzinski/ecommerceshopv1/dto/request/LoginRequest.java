package pl.bartoszsredzinski.ecommerceshopv1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Login request
 *
 * @author Bartosz Średziński
 * created on 24.02.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest{

    @NotBlank(message = "Login is mandatory")
    private String login;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
