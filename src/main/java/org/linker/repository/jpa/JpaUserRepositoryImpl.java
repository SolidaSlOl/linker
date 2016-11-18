package org.linker.repository.jpa;

import org.linker.model.domain.User;
import org.linker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaUserRepositoryImpl implements UserRepository {
    EntityManager em;

    @Autowired
    public JpaUserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User findById(Integer id) { //todo
        return null;
    }

    @Override
    public List<User> findAll() { //todo
        return null;
    }

    @Override
    public User save(User newInstance) { //todo
        return null;
    }
}
