package org.linker.repository.springdatajpa;

import org.linker.model.domain.Link;
import org.linker.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataLinkRepository extends JpaRepository<Link, Integer> {
    /**
     * Find most recently added 10 links.
     * @return Links found
     */
    List<Link> findFirst10ByOrderByIdDesc();

    /**
     * Find link by user.
     * @param user User used to find
     * @return Links found
     */
    List<Link> findByUser(User user);

    /**
     * Find links by tag name.
     * @param name Tag name
     * @return Links found
     */
    List<Link> findByTagsName(String name);
}
