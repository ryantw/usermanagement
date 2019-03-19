package io.lker.webstore.common.model.order;

import io.lker.webstore.common.model.user.BaseEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"order"})
@Entity
@Builder
@Slf4j
@Table(name = "delivery_type")
public class DeliveryType extends BaseEntity {

    @Column(name = "delivery_option")
    private String deliveryOption;

    @OneToOne
    private Order order;
}
