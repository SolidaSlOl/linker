package org.linker.repository.springdatajpa;

import org.linker.model.domain.Tag;
import org.linker.repository.TagRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataTagRepository extends CrudRepository<Tag, Integer>, TagRepository {

}
