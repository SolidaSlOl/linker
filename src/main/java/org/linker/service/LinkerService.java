package org.linker.service;

import org.linker.model.domain.Link;
import org.linker.model.domain.User;

import java.util.List;

public interface LinkerService {
    void saveUser(User user);

    List<Link> findLinksByUser();

    List<Link> findLinksByTagName(String tagName);

    Link findLink(Integer id);

    List<Link> findLastTenLinks();

    void saveLink(Link link);

    void updateLink(Link link);

    User findByUsername(String name);
}