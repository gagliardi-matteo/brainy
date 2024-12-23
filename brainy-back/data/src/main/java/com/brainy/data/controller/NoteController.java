package com.brainy.data.controller;

import com.brainy.data.domain.Note;
import com.brainy.data.service.NoteService;
import com.brainy.data.service.TaggingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:4200")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private TaggingServiceImpl taggingService;

    @GetMapping
    public List<Note> getNotesByUser(@RequestParam Long userId) {
        return noteService.getNotesByUser(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id, @RequestParam Long userId) {
        return noteService.getNoteById(id, userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Note createNote(@RequestBody String note, @RequestParam String email) throws Exception {
        List<String> tags = List.of(taggingService.generateTags(note));
        return noteService.createNote(note, tags, email);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable Long id,
            @RequestBody Note note,
            @RequestParam List<String> tags,
            @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(noteService.updateNote(id, note, tags, userId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id, @RequestParam Long userId) {
        noteService.deleteNote(id, userId);
        return ResponseEntity.noContent().build();
    }

}
