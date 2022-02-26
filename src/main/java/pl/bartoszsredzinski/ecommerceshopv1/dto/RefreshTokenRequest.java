package pl.bartoszsredzinski.ecommerceshopv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
    private String login;
}
