package org.linker.repository.jpa;

import org.linker.model.domain.Tag;
import org.linker.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaTagRepositoryImpl implements TagRepository {
    EntityManager em;

    @Autowired
    public JpaTagRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Tag findById(Integer id) { //todo
        return null;
    }

    @Override
    public List<Tag> findAll() { //todo
        return null;
    }

    @Override
    public Tag save(Tag newInstance) { //todo
        return null;
    }
}
