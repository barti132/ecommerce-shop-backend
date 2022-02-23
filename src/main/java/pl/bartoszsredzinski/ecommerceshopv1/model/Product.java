package pl.bartoszsredzinski.ecommerceshopv1.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class is representation of product model.
 *
 * @author Bartosz Średziński
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @NotNull
    @Column(length = 128)
    private String category;

    @NotNull
    @Column(length = 64)
    private String producer_name;

    @NotNull
    @Column(length = 32)
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Column(length = 128)
    private String img;

    @NotNull
    private BigDecimal price_net;

    @NotNull
    private BigDecimal price_gross;
}
