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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
    private static final String DELIMITER = " ";

    @Override
    public List<Tag> stringToTags(final String tagField) {
        List<Tag> result = new ArrayList<>();
        if (!tagField.isEmpty()) {
            Arrays.stream(tagField.split(DELIMITER))
                .forEach(t -> result.add(new Tag(t)));
        }
        return result;
    }

    @Override
    public String tagsToString(final List<Tag> tags) {
        String result = "";
        if (!tags.isEmpty()) {
            result = tags.stream()
                .map(Tag::getName)
                .collect(Collectors.joining(DELIMITER));
        }
        return result;
    }
}
