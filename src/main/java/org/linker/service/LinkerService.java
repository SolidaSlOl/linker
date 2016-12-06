package org.linker.service;

import org.linker.model.domain.Link;
import org.linker.model.domain.User;
import java.util.List;

public interface LinkerService {
    /**
     * Save user.
     * @param user User
     */
    void saveUser(User user);

    /**
     * Find links by user.
     * @return Links found
     */
    List<Link> findLinksByUser();

    /**
     * Find links by tag's name.
     * @param tagName Tag's name
     * @return Links
     */
    List<Link> findLinksByTagName(String tagName);

    /**
     * Find link by id.
     * @param id Id
     * @return Link
     */
    Link findLink(Integer id);

    /**
     * Find most recently added 10 links.
     * @return links
     */
    List<Link> findLastTenLinks();

    /**
     * Save link.
     * @param link Link
     */
    void saveLink(Link link);

    /**
     * Update link.
     * @param link Link
     */
    void updateLink(Link link);

    /**
     * Find user by username.
     * @param username Username
     * @return User
     */
    User findUserByUsername(String username);
}
