package org.linker.repository.springdatajpa;

import org.linker.model.domain.User;
import org.linker.repository.UserRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUserRepository extends CrudRepository<User, Integer>, UserRepository {
}
