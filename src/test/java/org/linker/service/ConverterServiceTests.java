package org.linker.service;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ConverterServiceTests {
    ConverterService converter = new ConverterServiceImpl();

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
}