package io.lker.webstore.usermanagement.services;

import io.lker.webstore.common.model.user.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService extends CrudService<Role, Long>  {
    Role findByRoleName(String name);
}
