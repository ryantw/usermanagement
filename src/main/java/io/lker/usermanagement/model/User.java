package io.lker.usermanagement.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Builder
    public User(Long id, String firstName, String lastName,
                String emailAddress, String password, Roles roles){
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

    @Enumerated(value = EnumType.STRING)
    private Roles roles;


}
