package io.lker.webstore.controllers;

import io.lker.webstore.common.model.product.Product;
import io.lker.webstore.usermanagement.services.springjpa.ProductJPAService;
import io.lker.webstore.usermanagement.services.springjpa.SizeJPAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final SizeJPAService sizeJPAService;
    private final ProductJPAService productJPAService;

    public ProductController(SizeJPAService sizeJPAService, ProductJPAService productJPAService) {
        this.sizeJPAService = sizeJPAService;
        this.productJPAService = productJPAService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Product save(@RequestBody Product product){
        log.info("SAVING PRODUCT");
        return productJPAService.save(product);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        Set<Product> response = productJPAService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        Product response = productJPAService.findById(id);
        return ResponseEntity.ok(response);
    }

}
