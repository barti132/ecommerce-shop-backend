package pl.bartoszsredzinski.ecommerceshopv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 24.02.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{
    private String authenticationToken;
    private String username;
}