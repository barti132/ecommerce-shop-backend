package pl.bartoszsredzinski.ecommerceshopv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String password;
}
