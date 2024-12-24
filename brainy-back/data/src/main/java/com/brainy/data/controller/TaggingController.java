package com.brainy.data.controller;

import com.brainy.data.domain.Note;
import com.brainy.data.service.TaggingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tags")
public class TaggingController {

    @Autowired
    private TaggingServiceImpl taggingService;

    @PostMapping("/generate")
    public String[] generateTags(@RequestParam String note) throws Exception {
        String[] tags = taggingService.generateTags(note);

        return tags;
    }
}
