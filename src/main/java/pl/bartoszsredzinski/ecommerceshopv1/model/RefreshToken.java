package pl.bartoszsredzinski.ecommerceshopv1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

/**
 * Refresh token model
 *
 * @author Bartosz Średziński
 * created on 26.02.2022
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Instant createdDate;
}
