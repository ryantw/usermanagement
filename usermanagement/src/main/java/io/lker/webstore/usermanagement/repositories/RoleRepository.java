package io.lker.webstore.usermanagement.repositories;

import io.lker.webstore.common.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String name);
}
