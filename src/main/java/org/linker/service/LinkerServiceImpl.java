/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2016 Mikita Herasiutsin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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

/**
 * Main persistence service.
 *
 * @since 1.0
 * @author Mikita Herasiutsin (mikita.herasiutsin@gmail.com)
 * @version $Id$
 */
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
        return this.linkRepository.findByUser(this.getCurrentUser());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Link> findLinksByTagName(final String tagName) {
        return this.linkRepository.findByTagsName(tagName);
    }

    @Override
    @Transactional(readOnly = true)
    public Link findLink(final Integer id) {
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
    public void saveUser(final User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(this.roleRepository.findAll()));
        this.userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateLink(final Link link) {
        this.linkRepository.save(link);
    }

    @Override
    @Transactional
    public void saveLink(final Link link) {
        link.setUser(this.getCurrentUser());
        link.setTags(this.converter.stringToTags(link.getTagsInString()));
        this.linkRepository.save(link);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByUsername(final String username) {
        return this.userRepository.findByUsername(username);
    }

    private User getCurrentUser() {
        return this.userRepository.findByUsername(SecurityContextHolder
            .getContext().getAuthentication().getName());
    }
}
