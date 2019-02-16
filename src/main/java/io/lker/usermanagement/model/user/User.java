package io.lker.usermanagement.model.user;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Slf4j
@Table(name = "user")
public class User extends BaseEntity {

    @Builder
    public User(Long id, String firstName, String lastName,
                String emailAddress, String password, Collection<Role> roles){
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.roles = roles;
    }

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;

    // todo
    // do not hard code email/username as
    // login identity, set here and use wherever.
    private String loginIdentity(){
        return this.emailAddress;
    }
}
