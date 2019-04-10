package io.lker.webstore.usermanagement.services.springjpa;

import io.lker.webstore.common.model.user.User;
import io.lker.webstore.usermanagement.repositories.RoleRepository;
import io.lker.webstore.usermanagement.repositories.UserRepository;
import io.lker.webstore.usermanagement.services.UserService;
import io.lker.webstore.usermanagement.util.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class UserJPAService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserJPAService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<User> findAllByLastNameLike(String lastName) {
        return userRepository.findAllByLastNameLike("%" + lastName + "%");
    }

    @Override
    public User findByEmailAddress(String email) {
        return userRepository.findByEmailAddress("%" + email + "%");
    }

    @Override
    public void disableUser(Long id) {
        User disableUser = this.findById(id);
        disableUser.setEnabled(false);
        this.save(disableUser);
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
        //object.setRoles(new HashSet<>(roleRepository.findAll()));
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
