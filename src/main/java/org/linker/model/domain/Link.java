package org.linker.model.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.linker.service.ConverterServiceImpl;

@Entity
@Table(name = "links")
public class Link extends BaseEntity {
    @Column(name = "original")
    private String original;

    /**
     * Shorten link is hash of Link's Id. So it is not stored directly in database
     * and should provide greater performance.
     */
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
        joinColumns = @JoinColumn(name = "link_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    @Transient
    @Pattern(regexp = "\\w+( \\w+)*", message = "Format should be like \"Tag_1 Tag_2 ... Tag_n\"")
    private String tagsInString;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
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
    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }
    public String getShorten() {
        return new ConverterServiceImpl().encode(this.getId());
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
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        return this.tags;
    }
    public List<Tag> getTags() {
        return this.getTagsInternal();
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public void addTag(Tag tag) {
        this.getTagsInternal().add(tag);
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
