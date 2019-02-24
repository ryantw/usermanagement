package io.lker.webstore.usermanagement.repositories;

import io.lker.webstore.common.model.product.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<ProductSize, Long> {
}
