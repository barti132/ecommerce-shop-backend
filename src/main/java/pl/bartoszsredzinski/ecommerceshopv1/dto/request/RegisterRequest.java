package pl.bartoszsredzinski.ecommerceshopv1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String email;
    private String login;
    private String password;
    private String name;
    private String lastName;
}
