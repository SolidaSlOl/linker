package org.linker.repository.springdatajpa;

import org.linker.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRoleRepository extends JpaRepository<Role, Integer> {
}
