package io.lker.webstore.usermanagement.services.springjpa;

import io.lker.webstore.common.model.order.DeliveryType;
import io.lker.webstore.usermanagement.repositories.DeliveryTypeRepository;
import io.lker.webstore.usermanagement.services.DeliveryTypeService;
import io.lker.webstore.usermanagement.util.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class DeliveryTypeJPAService implements DeliveryTypeService {

    private final DeliveryTypeRepository deliveryTypeRepository;

    public DeliveryTypeJPAService(DeliveryTypeRepository deliveryTypeRepository) {
        this.deliveryTypeRepository = deliveryTypeRepository;
    }

    @Override
    public Set<DeliveryType> findAll() {
        Set<DeliveryType> deliveryTypes = new HashSet<>();
        deliveryTypeRepository.findAll().forEach(deliveryTypes::add);
        return deliveryTypes;
    }

    @Override
    public DeliveryType findById(Long aLong) {
        return deliveryTypeRepository.findById(aLong)
                .orElseThrow(() -> new ProductNotFoundException(aLong));
    }

    @Override
    public DeliveryType save(DeliveryType object) {
        return deliveryTypeRepository.save(object);
    }

    @Override
    public void delete(DeliveryType object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
