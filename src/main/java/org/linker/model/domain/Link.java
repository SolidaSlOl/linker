package org.linker.model.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Shorten link is hash of Link's Id. So it is not stored directly in database and should provide
 * greater performance.
 */
@Entity
@Table(name = "links")
public class Link extends BaseEntity {
    /**
     * proof against offensive words (removed 'a', 'e', 'i', 'o' and 'u')
     * unambiguous (removed 'I', 'l', '1', 'O' and '0')
     *
     * Example output:
     * 123456789 <=> pgK8p
     */
    private static final String ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
    private static final int BASE = ALPHABET.length();

    @Column(name = "actual")
    private String actual;

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

    /**
     * takes an ID and turns it into a short string
     */
    public static String encode(int num) {
        StringBuilder str = new StringBuilder();
        while (num > 0) {
            str.insert(0, ALPHABET.charAt(num % BASE));
            num = num / BASE;
        }
        return str.toString();
    }

    /**
     * takes a short string and turns it into an ID
     */
    public static int decode(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        }
        return num;
    }
}