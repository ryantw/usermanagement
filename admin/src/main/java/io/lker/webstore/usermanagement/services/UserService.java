package io.lker.webstore.usermanagement.services;

import io.lker.webstore.common.model.user.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService extends CrudService<User, Long> {

    Set<User> findAllByLastNameLike(String lastName);
    User findByEmailAddress(String email);
}
