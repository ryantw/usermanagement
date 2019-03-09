package io.lker.webstore.controllers;

import io.lker.webstore.common.model.preorder.PreOrder;
import io.lker.webstore.usermanagement.services.springjpa.PreOrderJPAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/order/preorder")
public class PreOrderController {

    private final PreOrderJPAService preOrderJPAService;

    public PreOrderController(PreOrderJPAService preOrderJPAService) {
        this.preOrderJPAService = preOrderJPAService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public Set<PreOrder> findAll() {
        return preOrderJPAService.findAll();
    }
}
