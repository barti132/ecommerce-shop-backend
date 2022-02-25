package pl.bartoszsredzinski.ecommerceshopv1.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * This class is representation of user model.
 *
 * @author Bartosz Średziński
 * created on 22.02.2022
 */

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(length = 16)
    private String phoneNumber;

    @NotNull
    @NotBlank(message = "Email is required")
    @Column(length = 64)
    private String email;

    @NotNull
    @NotBlank(message = "Login is required")
    @Column(length = 64)
    private String login;

    @NotNull
    @NotBlank(message = "Password is required")
    private String password;

    @NotNull
    @Column(length = 32)
    private String name;

    @NotNull
    @Column(length = 32)
    private String lastName;

    @NotNull
    @Column(length = 32)
    private String role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Address> Addresses;

    @NotNull
    private boolean enabled;

}
