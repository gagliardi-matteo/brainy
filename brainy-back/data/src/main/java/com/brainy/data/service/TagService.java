package com.brainy.data.service;

import com.brainy.data.domain.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    public List<Tag> getAllTags();
    public Optional<Tag> getTagByName(String name);

    List<Tag> findByName(List<String> tagNames);
}
