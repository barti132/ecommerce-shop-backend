package pl.bartoszsredzinski.ecommerceshopv1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Cart item model
 *
 * @author Bartosz Średziński
 * created on 03.03.2022
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    private Integer amount;
}
