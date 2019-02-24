package io.lker.webstore.usermanagement.repositories;

import io.lker.webstore.common.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Set<User> findAllByLastNameLike(String lastName);
    User findByEmailAddress(String email);

}
