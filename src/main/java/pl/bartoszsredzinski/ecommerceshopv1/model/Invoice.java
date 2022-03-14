package pl.bartoszsredzinski.ecommerceshopv1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private Date InvoiceDate;

    @NotNull
    @OneToOne
    private User user;

    @NotNull
    @OneToOne
    private Address address;

    @NotNull
    private BigDecimal totalPriceGross;

    @NotNull
    private BigDecimal totalPriceNet;

    @NotNull
    private Integer totalItems;

    @NotNull
    private String cartNumber;

    @NotNull
    private String cardName;

    @NotNull
    @OneToMany(orphanRemoval = true)
    private List<CartItem> products = new ArrayList<>();
}
