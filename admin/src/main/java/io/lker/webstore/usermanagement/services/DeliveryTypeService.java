package io.lker.webstore.usermanagement.services;

import io.lker.webstore.common.model.order.DeliveryType;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryTypeService extends CrudService<DeliveryType, Long> {
}
