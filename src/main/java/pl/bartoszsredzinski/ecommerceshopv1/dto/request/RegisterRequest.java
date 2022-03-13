package pl.bartoszsredzinski.ecommerceshopv1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Register Request
 *
 * @author Bartosz Średziński
 * created on 23.02.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest{

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank(message = "Login is mandatory")
    private String login;

    @NotNull
    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
}
