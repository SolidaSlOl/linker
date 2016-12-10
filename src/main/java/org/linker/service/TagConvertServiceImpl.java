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

import java.util.ArrayList;
import java.util.List;
import org.linker.model.domain.Tag;
import org.springframework.stereotype.Service;

/**
 * Tag's converter.
 * @author Mikita Herasiutsin (mikita.herasiutsin@gmail.com)
 * @version 1.0
 * @since 1.0
 */
@Service
public class TagConvertServiceImpl implements TagConvertService {

    @Override
    public List<Tag> stringToTags(final String tagString) {
        if (tagString.isEmpty()) {
            return new ArrayList<>();
        }
        List<Tag> tags = new ArrayList<>();
        String[] tagsArray = tagString.split(" ");
        for (String tag : tagsArray) {
            tags.add(new Tag(tag));
        }
        return tags;
    }

    @Override
    public String tagsToString(final List<Tag> tags) {
        if (tags.isEmpty()){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Tag tag : tags) {
            builder.append(tag.getName()).append(" ");
        }
        builder.delete(builder.length() - 1, builder.length());
        return builder.toString();
    }
}
