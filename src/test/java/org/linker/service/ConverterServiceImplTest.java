package org.linker.service;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.linker.model.domain.Tag;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConverterServiceImplTest extends TestCase {
    private static String TEST_TAG_NAME_1 = "best";
    private static String TEST_TAG_NAME_2 = "food";
    private static String TEST_TAG_NAME_3 = "tasty";
    private static String TEST_TAG_STRING = "best " + "food " + "tasty";

    private ConverterServiceImpl converter = new ConverterServiceImpl();
    private List<Tag> testTags;

    @Before
    public void setUp() {
        this.testTags = new ArrayList<>();
        this.testTags.add(new Tag(TEST_TAG_NAME_1));
        this.testTags.add(new Tag(TEST_TAG_NAME_2));
        this.testTags.add(new Tag(TEST_TAG_NAME_3));
    }

    @Test
    public void testEncode_whenIdGreaterThanZero() {
        assertEquals(this.converter.encode(2), "4");
        assertEquals(this.converter.encode(456), "bZ");
        assertEquals(this.converter.encode(159841), "3dv9");
        assertEquals(this.converter.encode(875683234), "4ztrNx");
    }

    @Test
    public void testDecode_whenFormatIsCorrect() {
        assertEquals(this.converter.decode("4"), 2);
        assertEquals(this.converter.decode("bZ"), 456);
        assertEquals(this.converter.decode("3dv9"), 159841);
        assertEquals(this.converter.decode("4ztrNx"), 875683234);
    }

    @Test
    public void testDecode_whenCorrectStringFormat() {
        assertFalse(this.converter.decode("pgK8p") < 0);
        assertFalse(this.converter.decode("3jd_") < 0);
        assertFalse(this.converter.decode("DH8-d") < 0);
        assertFalse(this.converter.decode("cM8") < 0);
        assertFalse(this.converter.decode("37fg9") < 0);
    }

    @Test
    public void testEncode_whenIdNegativeInteger() {
        assertEquals(this.converter.encode(-1), "");
        assertEquals(this.converter.encode(-456), "");
        assertEquals(this.converter.encode(-456789), "");
        assertEquals(this.converter.encode(-678987645), "");
    }

    @Test
    public void testEncode_whenIdNull() {
        assertEquals(this.converter.encode(0), "");
    }

    @Test
    public void testDecode_whenIncorrectStringFormat() {
        assertFalse(this.converter.decode("aUI") > 0);
        assertFalse(this.converter.decode("(sa") > 0);
        assertFalse(this.converter.decode("!49") > 0);
        assertFalse(this.converter.decode("123") > 0);
        assertFalse(this.converter.decode(">?") > 0);
    }

    @Test
    public void testDecode_whenEmptyStrings() {
        assertEquals(this.converter.decode(""), 0);
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
