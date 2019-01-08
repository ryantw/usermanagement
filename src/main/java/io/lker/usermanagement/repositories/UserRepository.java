package io.lker.usermanagement.repositories;

import io.lker.usermanagement.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLastName(String lastName);
    User findByFirstName(String firstName);
    Set<User> findAllByLastNameLike(String lastName);
    List<User> findAllByFirstNameLike(String firstName);

}
