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
public class SubCategory implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id_sub_category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @NotNull
    @ManyToMany
    @JoinColumn(name = "id_category")
    private List<Product> products;

    @NotNull
    private char[] name;

    @NotNull
    private char[] description;

}
