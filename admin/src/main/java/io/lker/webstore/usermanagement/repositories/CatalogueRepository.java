package io.lker.webstore.usermanagement.repositories;

import io.lker.webstore.common.model.catalogue.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
}
