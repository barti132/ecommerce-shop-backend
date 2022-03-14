package pl.bartoszsredzinski.ecommerceshopv1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Cart model
 *
 * @author Bartosz Średziński
 * created on 02.03.2022
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Date updatedDate;

    @NotNull
    private BigDecimal totalPriceGross;

    @NotNull
    private BigDecimal totalPriceNet;

    @NotNull
    private Integer totalItems;

    @NotNull
    @OneToMany
    private List<CartItem> products = new ArrayList<>();
}
