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
    private char[] name;

    @NotNull
    private char[] description;

    @OneToOne(mappedBy = "category")
    private SubCategory subCategory;

    @OneToOne(mappedBy = "category")
    private Product product;
}
