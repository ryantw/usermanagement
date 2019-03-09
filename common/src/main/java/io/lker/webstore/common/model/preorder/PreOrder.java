package io.lker.webstore.common.model.preorder;

import io.lker.webstore.common.model.product.Product;
import io.lker.webstore.common.model.user.BaseEntity;
import io.lker.webstore.common.model.user.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"product"})
@Entity
@Builder
@Slf4j
@Table(name = "preorders")
public class PreOrder extends BaseEntity {

    @Column(name = "date_added")
    private Date dateAdded = new Date();

    @Column(name = "estimated_arrival_date")
    private Date estimatedArrival = new Date();

    @Enumerated(value = EnumType.STRING)
    private PreOrderStatus preOrderStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToMany
    @JoinTable(name = "preorder_users",
            joinColumns = @JoinColumn(name = "preorders_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> preOrderUsers = new HashSet<>();
}
