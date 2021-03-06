package io.lker.webstore.usermanagement.util;

import io.lker.webstore.common.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Component
@Slf4j
public class AuditInterceptor implements PreInsertEventListener {

    private EntityManagerFactory entityManagerFactory;

    public AuditInterceptor(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @PostConstruct
    private void init(){
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(this);
    }

    @Override
    public boolean onPreInsert(PreInsertEvent preInsertEvent) {
        log.info(preInsertEvent.getEntityName());
        if(preInsertEvent.getEntity() instanceof User){
            User user = (User) preInsertEvent.getEntity();
            log.info(user.getEmailAddress());
        }
        return false;
    }
}
