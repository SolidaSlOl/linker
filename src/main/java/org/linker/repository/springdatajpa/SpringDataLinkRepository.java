package org.linker.repository.springdatajpa;

import org.linker.model.domain.Link;
import org.linker.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataLinkRepository extends JpaRepository<Link, Integer> {
    List<Link> findFirst10ByOrderByIdDesc();

    List<Link> findByUser(User user);

    List<Link> findByTagsName(String name);
}
