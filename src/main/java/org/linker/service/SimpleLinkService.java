package org.linker.service;

import org.linker.model.domain.Link;
import org.linker.model.domain.User;

import java.util.List;

public interface SimpleLinkService {
    public User findUser(Integer id);
    public User saveUser(Integer id);
    public List<User> findAllUsers();
    public Link findLink(Integer id);
    public Link saveLink(Integer id);
    public List<Link> findLinksByPlayer(Integer id);
}