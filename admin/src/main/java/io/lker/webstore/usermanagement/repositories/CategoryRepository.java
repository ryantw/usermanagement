package io.lker.webstore.usermanagement.repositories;

import io.lker.webstore.common.model.catalogue.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
