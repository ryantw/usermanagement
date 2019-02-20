package io.lker.usermanagement.services.springjpa;

import io.lker.usermanagement.model.user.Role;
import io.lker.usermanagement.repositories.RoleRepository;
import io.lker.usermanagement.services.RoleService;
import io.lker.usermanagement.util.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class RoleJPAService implements RoleService {

    private final RoleRepository roleRepository;

    public RoleJPAService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> findAll() {
        Set<Role> users = new HashSet<>();
        roleRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public Role findById(Long aLong) {
        return roleRepository.findById(aLong)
                .orElseThrow(() -> new UserNotFoundException(aLong));
    }

    @Override
    public Role save(Role object) {
        return roleRepository.save(object);
    }

    @Override
    public void delete(Role object) {
        roleRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        roleRepository.deleteById(aLong);
    }


    @Override
    public Role findByRoleName(String name) {
        return roleRepository.findByRoleName(name);
    }
}
