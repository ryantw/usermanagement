package io.lker.webstore.common.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.lker.webstore.common.model.order.Order;
import io.lker.webstore.common.model.preorder.PreOrder;
import io.lker.webstore.common.model.product.ProductDescription;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Address> descriptions = new HashSet<>();

    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    //private Set<Order> orders = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "preOrderUsers")
    private Set<PreOrder> preOrders = new HashSet<>();

    public void addPreOrder(PreOrder preOrder){
        if(preOrders.contains(preOrder))
            preOrders.remove(preOrder);

        preOrders.add(preOrder);
    }
}
