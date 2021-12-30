package pl.bartoszsredzinski.ecommerceshopv1.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @ManyToMany
    @JoinColumn(name = "id_category")
    private List<Category> categories;

    @NotNull
    @ManyToMany
    @JoinColumn(name = "id_sub_category")
    private List<SubCategory> subCategories;

    private char[] producer_name;
    private char[] name;
    private String description;
    private char[] img;
}
