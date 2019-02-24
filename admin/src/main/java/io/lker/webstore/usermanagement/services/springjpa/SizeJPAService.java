package io.lker.webstore.usermanagement.services.springjpa;

import io.lker.webstore.common.model.product.ProductSize;
import io.lker.webstore.usermanagement.repositories.SizeRepository;
import io.lker.webstore.usermanagement.services.CrudService;
import io.lker.webstore.usermanagement.util.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class SizeJPAService implements CrudService<ProductSize, Long> {

    private SizeRepository sizeRepository;

    public SizeJPAService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public Set<ProductSize> findAll() {
        Set<ProductSize> productSize = new HashSet<>();
        sizeRepository.findAll().forEach(productSize::add);
        return productSize;
    }

    @Override
    public ProductSize findById(Long aLong) {
        return sizeRepository.findById(aLong)
                .orElseThrow(() -> new ProductNotFoundException(aLong));
    }

    @Override
    public ProductSize save(ProductSize object) {
        return sizeRepository.save(object);
    }

    @Override
    public void delete(ProductSize object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
