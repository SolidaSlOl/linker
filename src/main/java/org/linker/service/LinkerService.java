package org.linker.service;

import org.linker.model.domain.Link;
import org.linker.model.domain.User;

import java.util.List;

public interface LinkerService {
    User findUser(Integer id);

    void saveUser(User user);

    List<User> findUsersByTagName(String tagName);

    Link findLink(Integer id);

    List<Link> findLastTenLinks();

    void saveLink(Link link);

    List<Link> findLinksByPlayer(Integer id);

    User findByUsername(String name);
}