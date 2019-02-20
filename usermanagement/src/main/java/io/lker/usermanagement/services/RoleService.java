package io.lker.usermanagement.services;

import io.lker.usermanagement.model.user.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService extends CrudService<Role, Long>  {
    Role findByRoleName(String name);
}
