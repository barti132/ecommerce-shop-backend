package pl.bartoszsredzinski.ecommerceshopv1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.bartoszsredzinski.ecommerceshopv1.model.Address;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank(message = "Login is mandatory")
    private String login;

    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    private String phoneNumber;

    @NotNull
    private List<Address> addresses;
}
