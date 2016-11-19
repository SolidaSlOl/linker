package org.linker.model.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends NamedEntity {
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Link> links;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "link_tag",
            joinColumns = @JoinColumn(name="link_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Link> getLinksInternal() {
        if (this.links == null) {
            this.links = new ArrayList<>();
        }
        return links;
    }

    public List<Link> getLinks() {
        return getLinksInternal();
    }

    public void addLink(Link link) {
        getLinksInternal().add(link);
        link.setUser(this);
    }

    public List<Tag> getTagsInternal() {
        if(this.tags == null) {
            this.tags = new ArrayList<>();
        }
        return this.tags;
    }

    public List<Tag> getTags() {
        return getTagsInternal();
    }

    public void addTag(Tag tag) {
        getTagsInternal().add(tag);
    }
}