package org.linker.service;

import org.linker.model.domain.Link;
import org.linker.model.domain.User;
import org.linker.repository.LinkRepository;
import org.linker.repository.TagRepository;
import org.linker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class LinkerServiceImpl implements LinkerService {
    @Autowired
    LinkRepository linkRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    @Override
    @Transactional(readOnly = true)
    public User findUser(Integer id) { //todo
        return null;
    }

    @Override
    @Transactional
    public User saveUser(Integer id) { //todo
        return null;
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() { //todo
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Link findLink(Integer id) { //todo
        return null;
    }

    @Override
    @Transactional
    public Link saveLink(Integer id) { //todo
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Link> findLinksByPlayer(Integer id) { //todo
        return null;
    }
}