package io.lker.webstore.controllers;

import io.lker.webstore.common.model.product.ProductSize;
import io.lker.webstore.usermanagement.services.springjpa.SizeJPAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/sizes")
public class SizeController {

    private final SizeJPAService sizeJPAService;
    public SizeController(SizeJPAService sizeJPAService) {
        this.sizeJPAService = sizeJPAService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductSize save(@RequestBody ProductSize productSize){
        log.info("SAVING SIZE");
        return sizeJPAService.save(productSize);
    }

}
