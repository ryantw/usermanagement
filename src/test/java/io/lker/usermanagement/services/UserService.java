package io.lker.usermanagement.services;

import io.lker.usermanagement.model.User;

import java.util.List;

public interface UserService extends CrudService<User, Long> {

    User findByLastName(String lastName);
    User findByFirstName(String firstName);
    List<User> findAllByLastName(String lastName);
    List<User> findAllByFirstName(String firstName);

}
