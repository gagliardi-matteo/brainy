package com.brainy.data.service;

import com.brainy.data.domain.Tag;
import com.brainy.data.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepo tagRepository;

    // Trova tutti i tag
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    // Trova un tag per nome
    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> findByName(List<String> tagNames) {
        return tagRepository.findAllByName(tagNames);
    }

}
