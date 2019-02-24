package io.lker.webstore.usermanagement.services.springjpa;

import io.lker.webstore.common.model.product.Product;
import io.lker.webstore.usermanagement.repositories.ProductRepository;
import io.lker.webstore.usermanagement.services.CrudService;
import io.lker.webstore.usermanagement.services.ProductService;
import io.lker.webstore.usermanagement.util.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class ProductJPAService implements ProductService {

    private ProductRepository productRepository;

    public ProductJPAService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Set<Product> findAll() {
        Set<Product> products = new HashSet<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Set<Product> findByGroupedProduct(Long id) {
        return productRepository.findByGroupedProduct(id);
    }

    @Override
    public Product findById(Long aLong) {
        return productRepository.findById(aLong)
                .orElseThrow(() -> new UserNotFoundException(aLong));
    }

    @Override
    public Product save(Product object) {
        return productRepository.save(object);
    }

    @Override
    public void delete(Product object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
