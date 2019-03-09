package io.lker.webstore.usermanagement.services.springjpa;

import io.lker.webstore.common.model.preorder.PreOrder;
import io.lker.webstore.usermanagement.repositories.PreOrderRepository;
import io.lker.webstore.usermanagement.services.PreOrderService;
import io.lker.webstore.usermanagement.util.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class PreOrderJPAService implements PreOrderService {

    private PreOrderRepository preOrderRepository;

    public PreOrderJPAService(PreOrderRepository preOrderRepository) {
        this.preOrderRepository = preOrderRepository;
    }

    @Override
    public Set<PreOrder> findAll() {
        Set<PreOrder> preOrders = new HashSet<>();
        preOrderRepository.findAll().forEach(preOrders::add);
        return preOrders;
    }

    @Override
    public PreOrder findById(Long aLong) {
        return preOrderRepository.findById(aLong)
                .orElseThrow(() -> new UserNotFoundException(aLong));
    }

    @Override
    public PreOrder save(PreOrder object) {
        return preOrderRepository.save(object);
    }

    @Override
    public void delete(PreOrder object) {
        preOrderRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        preOrderRepository.deleteById(aLong);
    }
}
