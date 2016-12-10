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
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.linker.model.domain.Tag;

/**
 * Test case for {@link TagConvertServiceImpl}.
 * @author Mikita Herasiutsin (mikita.herasiutsin@gmail.com)
 * @version 1.0
 * @since 1.0
 */
public class TagConvertServiceImplTest {
    private static String TEST_TAG_NAME_1 = "best";
    private static String TEST_TAG_NAME_2 = "food";
    private static String TEST_TAG_NAME_3 = "tasty";
    private static String TEST_TAG_STRING = "best " + "food " + "tasty";


    private final TagConvertServiceImpl converter = new TagConvertServiceImpl();
    private final List<Tag> testTags = new ArrayList<>();

    @Before
    public void setUp() {
        this.testTags.add(new Tag(TEST_TAG_NAME_1));
        this.testTags.add(new Tag(TEST_TAG_NAME_2));
        this.testTags.add(new Tag(TEST_TAG_NAME_3));
    }

    @Test
    public void testTagsToString_whenEmptyTagList() {
        assertEquals("", this.converter.tagsToString(new ArrayList<>()));
    }

    @Test
    public void testTagsToString_whenTagListWithValues() {
        assertEquals(TEST_TAG_STRING, this.converter.tagsToString(testTags));
    }

    @Test
    public void testStringToTags_whenEmptyString() {
        assertTrue(this.converter.stringToTags("").isEmpty());
    }

    @Test
    public void testStringToTags_whenStringWithValues() {
        final List<Tag> tags = this.converter.stringToTags(TEST_TAG_STRING);
        assertEquals(tags.get(0).getName(), TEST_TAG_NAME_1);
        assertEquals(tags.get(1).getName(), TEST_TAG_NAME_2);
        assertEquals(tags.get(2).getName(), TEST_TAG_NAME_3);
    }
}
