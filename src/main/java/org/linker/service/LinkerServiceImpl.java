package org.linker.service;

import org.linker.model.domain.Link;
import org.linker.model.domain.User;
import org.linker.repository.springdatajpa.SpringDataLinkRepository;
import org.linker.repository.springdatajpa.SpringDataTagRepository;
import org.linker.repository.springdatajpa.SpringDataUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class LinkerServiceImpl implements LinkerService {
    SpringDataLinkRepository linkRepository;
    SpringDataUserRepository userRepository;
    SpringDataTagRepository tagRepository;

    @Override
    @Transactional(readOnly = true)
    public User findUser(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Link findLink(Integer id) {
        return linkRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Link> findLinksByPlayer(Integer id) { //todo
        return null;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveLink(Link link) {
        linkRepository.save(link);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByName(String name) {
        return userRepository.findByUsername(name);
    }
}