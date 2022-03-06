package pl.bartoszsredzinski.ecommerceshopv1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 06.03.2022
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Date InvoiceDate;

    @OneToOne
    private User user;

    @OneToOne
    private Address address;

    private BigDecimal totalPriceGross;

    private BigDecimal totalPriceNet;

    private Integer totalItems;

    @OneToMany(orphanRemoval = true)
    private List<CartItem> products = new ArrayList<>();
}
