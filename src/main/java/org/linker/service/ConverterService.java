package org.linker.service;

import org.linker.model.domain.Link;

import java.util.List;
import java.util.Map;

public interface ConverterService {
    String encode(Integer num);
    int decode(String string);
    Map<Link, String> encodeList(List<Link> links);
}
