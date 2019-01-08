package io.lker.usermanagement.services;

import io.lker.usermanagement.model.User;

import java.util.List;
import java.util.Set;

public interface UserService extends CrudService<User, Long> {

    User findByLastName(String lastName);
    User findByFirstName(String firstName);
    Set<User> findAllByLastNameLike(String lastName);
    List<User> findAllByFirstNameLike(String firstName);

}
