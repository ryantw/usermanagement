package io.lker.webstore.common.model.order;

import io.lker.webstore.common.model.user.BaseEntity;
import io.lker.webstore.common.model.user.User;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user"})
@Entity
@Builder
@Slf4j
@Table(name = "customer_order")
public class Order extends BaseEntity {

    @Column(name = "price")
    private BigDecimal price;

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    private DeliveryType deliveryType;

    @ManyToOne
    private User user;
}
