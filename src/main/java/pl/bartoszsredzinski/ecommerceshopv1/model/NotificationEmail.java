package pl.bartoszsredzinski.ecommerceshopv1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 23.02.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail{
    private String subject;
    private String recipient;
    private String body;
}
