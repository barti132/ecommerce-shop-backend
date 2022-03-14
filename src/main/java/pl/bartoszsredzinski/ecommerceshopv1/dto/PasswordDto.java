package pl.bartoszsredzinski.ecommerceshopv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Password data transfer object
 *
 * @author Bartosz Średziński
 * created on 28.02.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto{
    @NotNull
    @NotBlank(message = "Password is mandatory")
    private String password;
}
