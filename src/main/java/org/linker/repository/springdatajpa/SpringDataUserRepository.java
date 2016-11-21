package org.linker.repository.springdatajpa;

import org.linker.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
