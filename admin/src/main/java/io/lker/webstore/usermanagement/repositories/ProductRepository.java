package io.lker.webstore.usermanagement.repositories;

import io.lker.webstore.common.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Set<Product> findByGroupedProduct(Long id);
}
