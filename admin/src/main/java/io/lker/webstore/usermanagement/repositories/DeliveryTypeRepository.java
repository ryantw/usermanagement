package io.lker.webstore.usermanagement.repositories;

import io.lker.webstore.common.model.order.DeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTypeRepository extends JpaRepository<DeliveryType, Long> {
}
