package org.linker.service;

import org.linker.model.domain.Link;
import org.linker.model.domain.User;

import java.util.List;

public interface LinkerService {
    public User findUser(Integer id);

    public void saveUser(User user);

    public List<User> findAllUsers();

    public Link findLink(Integer id);

    public void saveLink(Link link);

    public List<Link> findLinksByPlayer(Integer id);

    public User findByName(String name);
}