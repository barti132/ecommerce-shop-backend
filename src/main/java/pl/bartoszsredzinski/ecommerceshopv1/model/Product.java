package pl.bartoszsredzinski.ecommerceshopv1.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id_product;

    @NotNull
    @Column(length = 128)
    private String category;

    @NotNull
    @Column(length = 128)
    private String sub_category;

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
}
