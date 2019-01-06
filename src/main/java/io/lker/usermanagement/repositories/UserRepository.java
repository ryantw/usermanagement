package io.lker.usermanagement.repositories;

import io.lker.usermanagement.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLastName(String lastName);
    User findByFirstName(String firstName);
    List<User> findAllByLastName(String lastName);
    List<User> findAllByFirstName(String firstName);

}
