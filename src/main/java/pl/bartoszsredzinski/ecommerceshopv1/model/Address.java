package pl.bartoszsredzinski.ecommerceshopv1.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class is representation of address model.
 *
 * @author Bartosz Średziński
 * created on 22.02.2022
 */

@Entity
@Table(name = "address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @NotNull
    @Column(length = 128)
    private String address;

    @NotNull
    @Column(length = 128)
    private String city;

    @NotNull
    @Column(length = 128)
    private String country;

    @NotNull
    @Column(length = 16)
    private String postal_code;
}
