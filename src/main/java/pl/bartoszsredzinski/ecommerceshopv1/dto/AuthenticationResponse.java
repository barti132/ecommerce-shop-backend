package pl.bartoszsredzinski.ecommerceshopv1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Authentication response
 *
 * @author Bartosz Średziński
 * created on 24.02.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{
    private String authenticationToken;
    private String refreshToken;
}
