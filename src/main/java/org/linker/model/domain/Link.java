package org.linker.model.domain;

import org.linker.service.ConverterServiceImpl;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "links")
public class Link extends BaseEntity {
    @Column(name = "original")
    private String original;

    @Transient
    private String shorten;

    @Column(name = "clicks")
    private Integer clicks = 0;

    @Size(min = 10, max = 150)
    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "link_tag",
            joinColumns = @JoinColumn(name="link_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;


    @Transient
    @Pattern(regexp = "\\w+( \\w+)*", message = "Format should be like \"Tag_1 Tag_2 ... Tag_n\"")
    private String tagsInString;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public Integer getClicks() {
        return clicks;
    }

    public String getShorten() {
        return new ConverterServiceImpl().encode(this.getId());
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public void addClick() {
        this.clicks++;
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

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return getTagsInternal();
    }

    public void addTag(Tag tag) {
        getTagsInternal().add(tag);
    }

    public String getTagsInString() {
        return this.tagsInString;
    }

    public void setTagsInString(String tagsInString) {
        this.tagsInString = tagsInString;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}