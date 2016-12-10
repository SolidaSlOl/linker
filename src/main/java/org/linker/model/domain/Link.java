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
package org.linker.model.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.linker.service.LinkConvertServiceImpl;

/**
 * Link entity.
 *
 * @since 1.0
 * @author Mikita Herasiutsin (mikita.herasiutsin@gmail.com)
 * @version $Id$
 */
@Entity
@Table(name = "links")
public class Link extends BaseEntity {
    private String original;

    /**
     * Shorten link is hash of Link's Id. So it is not stored directly in
     * database and should provide greater performance.
     */
    @Transient
    private String shorten;

    private Integer clicks = 0;

    @Size(min = 10, max = 150)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "link_tag",
        joinColumns = @JoinColumn(
            name = "link_id",
            referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "tag_id",
            referencedColumnName = "id"
        )
    )
    private List<Tag> tags;

    @Transient
    @Pattern(
        regexp = "\\w+( \\w+)*",
        message = "Format should be like \"Tag_1 Tag_2 ... Tag_n\""
    )
    private String tagsInString;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getOriginal() {
        return this.original;
    }

    public void setOriginal(final String original) {
        this.original = original;
    }

    public Integer getClicks() {
        return this.clicks;
    }
    public void setClicks(final Integer clicks) {
        this.clicks = clicks;
    }
    public String getShorten() {
        return new LinkConvertServiceImpl().encode(this.getId());
    }
    public void addClick() {
        this.clicks++;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
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

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(final Tag tag) {
        this.getTagsInternal().add(tag);
    }

    public String getTagsInString() {
        return this.tagsInString;
    }

    public void setTagsInString(final String tagsInString) {
        this.tagsInString = tagsInString;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
