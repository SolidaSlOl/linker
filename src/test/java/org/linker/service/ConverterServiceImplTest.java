package org.linker.service;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.linker.model.domain.Link;
import org.linker.model.domain.Tag;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
        testTags = new ArrayList<>();
        testTags.add(new Tag(TEST_TAG_NAME_1));
        testTags.add(new Tag(TEST_TAG_NAME_2));
        testTags.add(new Tag(TEST_TAG_NAME_3));
    }

    @Test
    public void testEncode_whenIdGreaterThanZero() {
        assertEquals(converter.encode(2), "4");
        assertEquals(converter.encode(456), "bZ");
        assertEquals(converter.encode(159841), "3dv9");
        assertEquals(converter.encode(875683234), "4ztrNx");
    }

    @Test
    public void testDecode_whenFormatIsCorrect() {
        assertEquals(converter.decode("4"), 2);
        assertEquals(converter.decode("bZ"), 456);
        assertEquals(converter.decode("3dv9"), 159841);
        assertEquals(converter.decode("4ztrNx"), 875683234);
    }

    @Test
    public void testDecode_whenCorrectStringFormat() {
        assertFalse(converter.decode("pgK8p") < 0);
        assertFalse(converter.decode("3jd_") < 0);
        assertFalse(converter.decode("DH8-d") < 0);
        assertFalse(converter.decode("cM8") < 0);
        assertFalse(converter.decode("37fg9") < 0);
    }

    @Test
    public void testEncode_whenIdNegativeInteger() {
        assertEquals(converter.encode(-1), "");
        assertEquals(converter.encode(-456), "");
        assertEquals(converter.encode(-456789), "");
        assertEquals(converter.encode(-678987645), "");
    }

    @Test
    public void testEncode_whenIdNull() {
        assertEquals(converter.encode(0), "");
    }

    @Test
    public void testDecode_whenIncorrectStringFormat() {
        assertFalse(converter.decode("aUI") > 0);
        assertFalse(converter.decode("(sa") > 0);
        assertFalse(converter.decode("!49") > 0);
        assertFalse(converter.decode("123") > 0);
        assertFalse(converter.decode(">?") > 0);
    }

    @Test
    public void testDecode_whenEmptyStrings() {
        assertEquals(converter.decode(""), 0);
    }

    @Test
    public void testTagsToString_whenEmptyTagList() {
        assertEquals("", converter.tagsToString(new ArrayList<>()));
    }

    @Test
    public void testTagsToString_whenTagListWithValues() {
        assertEquals(TEST_TAG_STRING, converter.tagsToString(testTags));
    }

    @Test
    public void testStringToTags_whenEmptyString() {
        assertTrue(converter.stringToTags("").isEmpty());
    }

    @Test
    public void testStringToTags_whenStringWithValues() {
        List<Tag> tags = converter.stringToTags(TEST_TAG_STRING);
        assertEquals(tags.get(0).getName(), TEST_TAG_NAME_1);
        assertEquals(tags.get(1).getName(), TEST_TAG_NAME_2);
        assertEquals(tags.get(2).getName(), TEST_TAG_NAME_3);
    }
}