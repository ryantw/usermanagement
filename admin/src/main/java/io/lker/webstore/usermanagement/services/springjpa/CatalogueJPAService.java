package io.lker.webstore.usermanagement.services.springjpa;

import io.lker.webstore.common.model.catalogue.Catalogue;
import io.lker.webstore.usermanagement.repositories.CatalogueRepository;
import io.lker.webstore.usermanagement.services.CatalogueService;
import io.lker.webstore.usermanagement.util.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class CatalogueJPAService implements CatalogueService {

    private final CatalogueRepository catalogueRepository;

    public CatalogueJPAService(CatalogueRepository catalogueRepository) {
        this.catalogueRepository = catalogueRepository;
    }

    @Override
    public Set<Catalogue> findAll() {
        Set<Catalogue> catalogues = new HashSet<>();
        catalogueRepository.findAll().forEach(catalogues::add);
        return catalogues;
    }

    @Override
    public Catalogue findById(Long aLong) {
        return catalogueRepository.findById(aLong)
                .orElseThrow(() -> new ProductNotFoundException(aLong));
    }

    @Override
    public Catalogue save(Catalogue object) {
        return catalogueRepository.save(object);
    }

    @Override
    public void delete(Catalogue object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
