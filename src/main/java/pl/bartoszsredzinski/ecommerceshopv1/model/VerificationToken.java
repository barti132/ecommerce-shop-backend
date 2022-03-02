package pl.bartoszsredzinski.ecommerceshopv1.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;

/**
 * Verification token
 *
 * @author Bartosz Średziński
 * created on 23.02.2022
 */

@Entity
@Table(name = "token")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String token;

    @OneToOne(fetch = LAZY)
    private User user;

    private Instant expiryDate;
}
