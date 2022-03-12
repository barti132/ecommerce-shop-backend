package pl.bartoszsredzinski.ecommerceshopv1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String login;
    private String password;
}
