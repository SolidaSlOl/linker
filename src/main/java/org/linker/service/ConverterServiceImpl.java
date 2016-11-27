package org.linker.service;

import org.linker.model.domain.Link;
import org.linker.model.domain.Tag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Override
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
    @Override
    public int decode(String str) {
        Integer num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        }
        return num;
    }

    @Override
    public List<Tag> stringToTags(String tagString) {
        if(tagString.isEmpty()) {
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
    public String tagsToString(List<Tag> tags) {
        if(tags.isEmpty()){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Tag tag : tags) {
            sb.append(tag.getName()).append(" ");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}
