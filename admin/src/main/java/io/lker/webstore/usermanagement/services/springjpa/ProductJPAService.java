package io.lker.webstore.usermanagement.services.springjpa;

import io.lker.webstore.common.model.product.Product;
import io.lker.webstore.usermanagement.repositories.ProductDescriptionRepository;
import io.lker.webstore.usermanagement.repositories.ProductRepository;
import io.lker.webstore.usermanagement.services.ProductService;
import io.lker.webstore.usermanagement.util.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class ProductJPAService implements ProductService {

    private ProductRepository productRepository;
    private ProductDescriptionRepository productDescriptionRepository;

    public ProductJPAService(ProductRepository productRepository, ProductDescriptionRepository productDescriptionRepository) {
        this.productRepository = productRepository;
        this.productDescriptionRepository = productDescriptionRepository;
    }

    @Override
    public Set<Product> findAll() {
        Set<Product> products = new HashSet<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product findById(Long aLong) {
        return productRepository.findById(aLong)
                .orElseThrow(() -> new ProductNotFoundException(aLong));
    }

    @Override
    public Product save(Product object) {
        //object.getDescriptions().forEach((productDescription -> productDescriptionRepository.save(productDescription)));
        return productRepository.save(object);
    }

    @Override
    public void delete(Product object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
