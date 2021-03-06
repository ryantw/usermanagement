package io.lker.webstore.usermanagement.services;

import io.lker.webstore.common.model.product.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService extends CrudService<Product, Long> {

}
