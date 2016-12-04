package org.linker.service;

import org.linker.model.domain.Link;
import org.linker.model.domain.User;
import org.linker.repository.springdatajpa.SpringDataLinkRepository;
import org.linker.repository.springdatajpa.SpringDataRoleRepository;
import org.linker.repository.springdatajpa.SpringDataUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Transactional
@Service
public class LinkerServiceImpl implements LinkerService {
    @Autowired
    private SpringDataLinkRepository linkRepository;
    @Autowired
    private SpringDataUserRepository userRepository;
    @Autowired
    private SpringDataRoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ConverterService converter;

    @Override
    @Transactional(readOnly = true)
    public List<Link> findLinksByUser() {
        return linkRepository.findByUser(getCurrentUser());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Link> findLinksByTagName(String tagName) {
        return linkRepository.findByTagsName(tagName);
    }

    @Override
    @Transactional(readOnly = true)
    public Link findLink(Integer id) {
        Link link = linkRepository.findOne(id);
        link.setTagsInString(converter.tagsToString(link.getTags()));
        return link;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Link> findLastTenLinks() {
        return linkRepository.findFirst10ByOrderByIdDesc();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateLink(Link link) {
        linkRepository.save(link);
    }

    @Override
    @Transactional
    public void saveLink(Link link) {
        link.setUser(getCurrentUser());
        link.setTags(converter.stringToTags(link.getTagsInString()));
        linkRepository.save(link);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    private User getCurrentUser() {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}