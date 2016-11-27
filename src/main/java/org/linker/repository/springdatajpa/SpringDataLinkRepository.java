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

//    @Query(value = "select link from Link link left join link.tags.name name where name = ?1")
//    @Query(value = "SELECT * FROM links LEFT JOIN tags t on t.name = ?1", nativeQuery = true)
    List<Link> findByTagsName(String name);
}
