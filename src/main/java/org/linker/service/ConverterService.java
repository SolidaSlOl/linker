package org.linker.service;

import org.linker.model.domain.Tag;
import java.util.List;

public interface ConverterService {
    /**
     * Takes an id and turns it into a short string.
     * @param num Id
     * @return String
     */
    String encode(Integer num);

    /**
     * Takes a short string and turns it into an link's id.
     * @param string String
     * @return Id
     */
    int decode(String string);

    /**
     * Take string with tags and turn it into list of tags
     * @param tagString String with tags
     * @return Tags
     */
    List<Tag> stringToTags(String tagString);

    /**
     * Take list of tags and turn it into a string with tags.
     * @param tags Tags
     * @return String
     */
    String tagsToString(List<Tag> tags);
}
