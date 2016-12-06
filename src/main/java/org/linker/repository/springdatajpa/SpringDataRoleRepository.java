package org.linker.repository.springdatajpa;

import org.linker.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRoleRepository extends JpaRepository<Role, Integer> {
    /**
     * Find role by its name
     * @param name Role name
     * @return Role found
     */
    Role findByName(String name);
}
