package pl.bartoszsredzinski.ecommerceshopv1.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * This class is representation of user model.
 *
 * @author Bartosz Średziński
 * created on 22.02.2022
 */

@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;

    @Column(length = 16)
    private String phoneNumber;

    @NotNull
    @Column(length = 64)
    private String email;

    @NotNull
    @Column(length = 64)
    private String login;

    @NotNull
    private String password;

    @NotNull
    @Column(length = 32)
    private String name;

    @NotNull
    @Column(length = 32)
    private String lastName;

    @NotNull
    private String role;

    @OneToMany
    @JoinTable(name="user_adresses")
    private List<Address> Addresses;

}
