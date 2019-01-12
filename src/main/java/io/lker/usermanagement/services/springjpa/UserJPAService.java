package io.lker.usermanagement.services.springjpa;

import io.lker.usermanagement.model.User;
import io.lker.usermanagement.repositories.UserRepository;
import io.lker.usermanagement.services.UserService;
import io.lker.usermanagement.util.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserJPAService implements UserService {

    private final UserRepository userRepository;

    // Constructor injection
    public UserJPAService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> findAllByLastNameLike(String lastName) {
        return userRepository.findAllByLastNameLike("%" + lastName + "%");
    }

    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User findById(Long aLong) {
        return userRepository.findById(aLong)
                .orElseThrow(() -> new UserNotFoundException(aLong));
    }

    @Override
    public User save(User object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
}
