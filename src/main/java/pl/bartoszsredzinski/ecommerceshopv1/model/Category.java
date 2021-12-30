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
public class Category implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id_category;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategories;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products;
}
