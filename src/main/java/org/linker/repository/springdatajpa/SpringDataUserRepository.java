package org.linker.repository.springdatajpa;

import org.linker.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<User, Integer> {
    /**
     * Find user by its name.
     * @param username Username
     * @return User found
     */
    User findByUsername(String username);
}
