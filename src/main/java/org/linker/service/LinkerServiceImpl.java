package org.linker.service;

import java.util.HashSet;
import java.util.List;
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
        return this.linkRepository.findByUser(getCurrentUser());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Link> findLinksByTagName(String tagName) {
        return this.linkRepository.findByTagsName(tagName);
    }

    @Override
    @Transactional(readOnly = true)
    public Link findLink(Integer id) {
        Link link = this.linkRepository.findOne(id);
        link.setTagsInString(this.converter.tagsToString(link.getTags()));
        return link;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Link> findLastTenLinks() {
        return this.linkRepository.findFirst10ByOrderByIdDesc();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(this.roleRepository.findAll()));
        this.userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateLink(Link link) {
        this.linkRepository.save(link);
    }

    @Override
    @Transactional
    public void saveLink(Link link) {
        link.setUser(getCurrentUser());
        link.setTags(this.converter.stringToTags(link.getTagsInString()));
        this.linkRepository.save(link);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    private User getCurrentUser() {
        return this.userRepository.findByUsername(SecurityContextHolder
            .getContext().getAuthentication().getName());
    }
}
