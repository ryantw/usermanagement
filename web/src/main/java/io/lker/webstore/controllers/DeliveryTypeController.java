package io.lker.webstore.controllers;

import io.lker.webstore.common.model.order.DeliveryType;
import io.lker.webstore.usermanagement.services.springjpa.DeliveryTypeJPAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/order/deliverytype")
public class DeliveryTypeController {

    private final DeliveryTypeJPAService deliveryTypeJPAService;

    public DeliveryTypeController(DeliveryTypeJPAService deliveryTypeJPAService) {
        this.deliveryTypeJPAService = deliveryTypeJPAService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public Set<DeliveryType> findAll() { return deliveryTypeJPAService.findAll(); }
}
