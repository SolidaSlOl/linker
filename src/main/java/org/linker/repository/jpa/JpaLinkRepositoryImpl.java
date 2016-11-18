package org.linker.repository.jpa;

import org.linker.model.domain.Link;
import org.linker.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaLinkRepositoryImpl implements LinkRepository {
    EntityManager em;

    @Autowired
    public JpaLinkRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Link findById(Integer id) { //todo
        return null;
    }

    @Override
    public List<Link> findAll() { //todo
        return null;
    }

    @Override
    public Link save(Link newInstance) { //todo
        return null;
    }
}
