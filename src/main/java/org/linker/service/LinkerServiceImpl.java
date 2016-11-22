package org.linker.service;

import org.linker.model.domain.Link;
import org.linker.model.domain.User;
import org.linker.repository.springdatajpa.SpringDataLinkRepository;
import org.linker.repository.springdatajpa.SpringDataRoleRepository;
import org.linker.repository.springdatajpa.SpringDataTagRepository;
import org.linker.repository.springdatajpa.SpringDataUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Transactional
@Service
public class LinkerServiceImpl implements LinkerService {
    @Autowired
    SpringDataLinkRepository linkRepository;

    @Autowired
    SpringDataUserRepository userRepository;

    @Autowired
    SpringDataTagRepository tagRepository;

    @Autowired
    SpringDataRoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User findUser(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findUsersByTagName(String tagName) {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Link findLink(Integer id) {
        return linkRepository.findOne(id);
    }

    @Override
    public List<Link> findLastTenLinks() {
        return linkRepository.findFirst10ByOrderByIdDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Link> findLinksByPlayer(Integer id) { //todo
        return null;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public void saveLink(Link link) {
        linkRepository.save(link);
    }

    @Override
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }
}