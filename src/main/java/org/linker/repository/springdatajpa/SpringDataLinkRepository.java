package org.linker.repository.springdatajpa;

import org.linker.model.domain.Link;
import org.linker.repository.LinkRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataLinkRepository extends CrudRepository<Link, Integer>, LinkRepository {
}
