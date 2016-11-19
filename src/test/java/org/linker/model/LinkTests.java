package org.linker.model;

import org.junit.Test;
import org.linker.model.domain.Link;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class LinkTests {
    List<String> correctStringList = Arrays.asList("pgK8p", "3jd_", "DH8-d", "cM8", "37fg9");
    List<Integer> correctIntList = Arrays.asList(2, 456, 159841, 55735, 875683234);
    List<String> incorrectStringList = Arrays.asList("aUi", "(sa", "!49", "123", ">?");
    List<Integer> incorrectIntList = Arrays.asList(-1, -456, -159841, -55735, -875683234);

    @Test
    public void shouldDecodeThenEncodeStringsToSameValues()
    {
        for (String str : correctStringList){
            assertThat(Link.encode(Link.decode(str)), is(equalTo(str)));
        }
    }
    @Test
    public void shouldEncodeThenDecodeIntegersToSameValues()
    {
        for (Integer num : correctIntList){
            assertThat(Link.decode(Link.encode(num)), is(equalTo(num)));
        }
    }

    @Test
    public void shouldEncodeNegativeIntegersToEmptyStrings()
    {
        for (Integer num : incorrectIntList){
            assertThat(Link.encode(num), is(equalTo("")));
        }
    }

    @Test
    public void shouldEncodeZeroToEmptyStrings()
    {
        for (Integer num : incorrectIntList){
            assertThat(Link.encode(0), is(equalTo("")));
        }
    }

    @Test
    public void shouldDecodeIncorrectStringsToNegativeIntegers()
    {
        for (String str : incorrectStringList){
            assertThat(Link.decode(str), is(lessThan(0)));
        }
    }

    @Test
    public void shouldDecodeEmptyStringsToZero()
    {
        assertThat(Link.decode(""), is(equalTo(0)));
    }
}