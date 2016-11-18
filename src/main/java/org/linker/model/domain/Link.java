package org.linker.model.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "links")
public class Link extends BaseEntity {
    @Column(name = "actual")
    private String actual;

    @Column(name = "generated")
    private String generated;

    @Column(name = "clicks")
    private Integer clicks;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "link_tag",
            joinColumns = @JoinColumn(name="link_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public String getGenerated() {
        return generated;
    }

    public void setGenerated(String generated) {
        this.generated = generated;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private List<Tag> getTagsInternal() {
        if(this.tags == null) {
            this.tags = new ArrayList<>();
        }
        return this.tags;
    }

    private void setTagsInternal(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return getTagsInternal();
    }

    public void addTag(Tag tag) {
        getTagsInternal().add(tag);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}