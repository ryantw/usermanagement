package io.lker.usermanagement.services;

import io.lker.usermanagement.model.user.User;

import java.util.Set;

public interface UserService extends CrudService<User, Long> {

    Set<User> findAllByLastNameLike(String lastName);
    User findByEmailAddress(String email);
}
