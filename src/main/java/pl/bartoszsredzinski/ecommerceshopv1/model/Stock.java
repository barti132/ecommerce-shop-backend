package pl.bartoszsredzinski.ecommerceshopv1.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Stock model
 *
 * @author Bartosz Średziński
 * created on 05.03.2022
 */
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne
    @NotNull
    private Product product;

    @NotNull
    private Integer amount;

    @NotNull
    private Date updatedDate;
}
