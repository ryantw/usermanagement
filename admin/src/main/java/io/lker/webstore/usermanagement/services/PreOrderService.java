package io.lker.webstore.usermanagement.services;

import io.lker.webstore.common.model.preorder.PreOrder;
import org.springframework.stereotype.Service;

@Service
public interface PreOrderService extends CrudService<PreOrder, Long> {
}
