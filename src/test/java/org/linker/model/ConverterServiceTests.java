package org.linker.model;

import org.junit.Test;
import org.linker.service.ConverterService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ConverterServiceTests {
    List<String> correctStringList = Arrays.asList("pgK8p", "3jd_", "DH8-d", "cM8", "37fg9");
    List<Integer> correctIntList = Arrays.asList(2, 456, 159841, 55735, 875683234);
    List<String> incorrectStringList = Arrays.asList("aUi", "(sa", "!49", "123", ">?");
    List<Integer> incorrectIntList = Arrays.asList(-1, -456, -159841, -55735, -875683234);

    @Test
    public void testDecode_whenIdGreaterThanZero() {
        assertThat(ConverterService.encode(2), anyOf(is(any(String.class)), notNullValue(String.class)));
        assertThat(ConverterService.encode(456), anyOf(is(any(String.class)), notNullValue(String.class)));
        assertThat(ConverterService.encode(159841), anyOf(is(any(String.class)), notNullValue(String.class)));
        assertThat(ConverterService.encode(875683234), anyOf(is(any(String.class)), notNullValue(String.class)));
    }

    @Test
    public void testEncode_whenCorrectStringFormat() {
        assertThat(ConverterService.decode("pgK8p"), is(greaterThan(0)));
        assertThat(ConverterService.decode("3jd_"), is(greaterThan(0)));
        assertThat(ConverterService.decode("DH8-d"), is(greaterThan(0)));
        assertThat(ConverterService.decode("cM8"), is(greaterThan(0)));
        assertThat(ConverterService.decode("37fg9"), is(greaterThan(0)));
    }

    @Test
    public void testEncode_whenIdNegativeInteger() {
        assertThat(ConverterService.encode(-1), is(equalTo("")));
        assertThat(ConverterService.encode(-456), is(equalTo("")));
        assertThat(ConverterService.encode(-456789), is(equalTo("")));
        assertThat(ConverterService.encode(-678987645), is(equalTo("")));
    }

    @Test
    public void testEncode_whenIdNull() {
        assertThat(ConverterService.encode(0), is(equalTo("")));
    }

    @Test
    public void testDecode_whenIncorrectStringFormat() {
        assertThat(ConverterService.decode("aUi"), is(lessThan(0)));
        assertThat(ConverterService.decode("(sa"), is(lessThan(0)));
        assertThat(ConverterService.decode("!49"), is(lessThan(0)));
        assertThat(ConverterService.decode("123"), is(lessThan(0)));
        assertThat(ConverterService.decode(">?"), is(lessThan(0)));
    }

    @Test
    public void testDecode_whenEmptyStrings() {
        assertThat(ConverterService.decode(""), is(equalTo(0)));
    }
}