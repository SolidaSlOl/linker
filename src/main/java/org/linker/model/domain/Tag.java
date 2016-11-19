package org.linker.model.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag extends NamedEntity {
    @ManyToMany
    @JoinTable(
            name = "link_tag",
            joinColumns = @JoinColumn(name="tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "link_id", referencedColumnName = "id"))
    private List<Link> links;

    @ManyToMany
    @JoinTable(
            name = "user_tag",
            joinColumns = @JoinColumn(name="tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users;

    private List<Link> getLinksInternal() {
        if(this.links == null) {
            this.links = new ArrayList<>();
        }
        return links;
    }

    public List<Link> getLinks() {
        return getLinksInternal();
    }

    public void addLink(Link link) {
        this.links.add(link);
    }

    private List<User> getUsersInternal() {
        if(this.users == null) {
            this.users = new ArrayList<>();
        }
        return this.users;
    }

    public List<User> getUsers() {
        return getUsersInternal();
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}