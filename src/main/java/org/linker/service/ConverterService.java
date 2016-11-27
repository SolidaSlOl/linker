package org.linker.service;

import org.linker.model.domain.Tag;

import java.util.List;

public interface ConverterService {
    String encode(Integer num);

    int decode(String string);

    List<Tag> stringToTags(String tagString);

    String tagsToString(List<Tag> tags);
}
