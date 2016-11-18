package org.linker.service;

import org.linker.model.domain.Link;
import org.linker.model.domain.User;
import org.linker.repository.LinkRepository;
import org.linker.repository.TagRepository;
import org.linker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SimpleLinkServiceImpl implements SimpleLinkService {
    LinkRepository linkRepository;
    UserRepository userRepository;
    TagRepository tagRepository;

    @Autowired
    public SimpleLinkServiceImpl(
            LinkRepository linkRepository,
            UserRepository userRepository,
            TagRepository tagRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public User findUser(Integer id) { //todo
        return null;
    }

    @Override
    public User saveUser(Integer id) { //todo
        return null;
    }

    @Override
    public List<User> findAllUsers() { //todo
        return null;
    }

    @Override
    public Link findLink(Integer id) { //todo
        return null;
    }

    @Override
    public Link saveLink(Integer id) { //todo
        return null;
    }

    @Override
    public List<Link> findLinksByPlayer(Integer id) { //todo
        return null;
    }
}