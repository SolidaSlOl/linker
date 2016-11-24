package org.linker.service;

import org.linker.model.domain.Link;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shorten link is hash of Link's Id. So it is not stored directly in database and should provide
 * greater performance.
 */
@Service
public class ConverterServiceImpl implements ConverterService {
    /**
     * proof against offensive words (removed 'a', 'e', 'i', 'o' and 'u')
     * unambiguous (removed 'I', 'l', '1', 'O' and '0')
     *
     * Example output:
     * 123456789 <=> pgK8p
     */
    private static final String ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
    private static final Integer BASE = ALPHABET.length();

    /**
     * takes an ID and turns it into a short string
     */
    public String encode(Integer num) {
        StringBuilder str = new StringBuilder();
        while (num > 0) {
            str.insert(0, ALPHABET.charAt(num % BASE));
            num = num / BASE;
        }
        return str.toString();
    }

    /**
     * takes a short string and turns it into an ID
     */
    public int decode(String str) {
        Integer num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        }
        return num;
    }
}
