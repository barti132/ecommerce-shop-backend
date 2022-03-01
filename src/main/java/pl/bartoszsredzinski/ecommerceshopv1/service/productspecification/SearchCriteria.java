package pl.bartoszsredzinski.ecommerceshopv1.service.productspecification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Search criteria model
 *
 * @author Bartosz Średziński
 * created on 01.03.2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria{
    private String key;
    private String operation;
    private String value;
}
