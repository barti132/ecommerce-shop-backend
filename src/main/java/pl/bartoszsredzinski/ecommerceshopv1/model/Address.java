package pl.bartoszsredzinski.ecommerceshopv1.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Address model
 *
 * @author Bartosz Średziński
 * created on 22.02.2022
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
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
    private String postalCode;
}
