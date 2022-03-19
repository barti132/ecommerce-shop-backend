package pl.bartoszsredzinski.ecommerceshopv1.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Product model
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
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 128)
    private String category;

    @NotNull
    @Column(length = 64)
    private String producerName;

    @NotNull
    @Column(length = 32)
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Column(length = 128)
    private String img;

    @NotNull
    private BigDecimal priceNet;

    @NotNull
    private BigDecimal priceGross;

    @NotNull
    private Boolean available;
}