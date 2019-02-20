package io.lker.usermanagement.repositories;

import io.lker.usermanagement.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {

    Set<User> findAllByLastNameLike(String lastName);
    User findByEmailAddress(String email);

}