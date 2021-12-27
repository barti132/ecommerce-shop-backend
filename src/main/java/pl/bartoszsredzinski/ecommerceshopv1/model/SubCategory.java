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
public class SubCategory implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id_sub_category;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_category")
    private Product product;

    @NotNull
    private char[] name;

    @NotNull
    private char[] description;

}
