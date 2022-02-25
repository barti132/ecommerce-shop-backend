package pl.bartoszsredzinski.ecommerceshopv1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User data transfer object
 *
 * @author Bartosz Średziński
 * created on 24.02.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto{
    private String email;
    private String login;
    private String name;
    private String lastName;
    private String phoneNumber;
}
