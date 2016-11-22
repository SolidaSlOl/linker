package org.linker.service;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ConverterServiceTests {
    ConverterService converter = new ConverterServiceImpl();

    @Test
    public void testDecode_whenIdGreaterThanZero() {
        assertThat(converter.encode(2), any(String.class));
        assertThat(converter.encode(456), any(String.class));
        assertThat(converter.encode(159841), any(String.class));
        assertThat(converter.encode(875683234), any(String.class));
    }

    @Test
    public void testEncode_whenCorrectStringFormat() {
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