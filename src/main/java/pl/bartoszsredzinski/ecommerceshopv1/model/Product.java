package pl.bartoszsredzinski.ecommerceshopv1.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
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
    @OneToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_sub_category")
    private SubCategory subCategory;

    private char[] producer_name;
    private char[] name;
    private String description;
    private char[] img;
}
