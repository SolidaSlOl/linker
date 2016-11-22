package org.linker.repository.springdatajpa;

import org.linker.model.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataLinkRepository extends JpaRepository<Link, Integer> {
    List<Link> findFirst10ByOrderByIdDesc ();
}
