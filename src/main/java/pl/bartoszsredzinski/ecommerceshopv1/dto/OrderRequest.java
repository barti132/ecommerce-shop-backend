package pl.bartoszsredzinski.ecommerceshopv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 05.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest{
    private Long addressId;
}
