package pl.bartoszsredzinski.ecommerceshopv1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * User Status Request
 *
 * @author Bartosz Średziński
 * created on 17.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusRequest{

    @NotNull
    private Long id;

    @NotNull
    private Boolean status;
}
