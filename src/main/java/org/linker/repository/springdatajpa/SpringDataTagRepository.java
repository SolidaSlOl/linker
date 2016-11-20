package org.linker.repository.springdatajpa;

import org.linker.model.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataTagRepository extends JpaRepository<Tag, Integer> {
}
