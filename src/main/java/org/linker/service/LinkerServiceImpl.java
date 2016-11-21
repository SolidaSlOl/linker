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
    SpringDataLinkRepository springDataLinkRepository;

    @Autowired
    SpringDataUserRepository springDataUserRepository;

    @Autowired
    SpringDataTagRepository springDataTagRepository;

    @Autowired
    SpringDataRoleRepository springDataRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User findUser(Integer id) {
        return springDataUserRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return springDataUserRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Link findLink(Integer id) {
        return springDataLinkRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Link> findLinksByPlayer(Integer id) { //todo
        return null;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(springDataRoleRepository.findAll()));
        springDataUserRepository.save(user);
    }

    @Override
    public void saveLink(Link link) {
        springDataLinkRepository.save(link);
    }

    @Override
    public User findByUsername(String name) {
        return springDataUserRepository.findByUsername(name);
    }
}