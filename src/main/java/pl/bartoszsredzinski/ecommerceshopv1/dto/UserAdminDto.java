package pl.bartoszsredzinski.ecommerceshopv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User data transfer object for admin
 *
 * @author Bartosz Średziński
 * created on 16.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminDto{
    private Long id;
    private String email;
    private String name;
    private String lastName;
    private String role;
    private boolean enabled;
}
