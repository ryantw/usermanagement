package io.lker.webstore.usermanagement.repositories;

import io.lker.webstore.common.model.preorder.PreOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreOrderRepository extends JpaRepository<PreOrder, Long> {
}
