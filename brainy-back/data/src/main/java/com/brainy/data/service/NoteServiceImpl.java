package com.brainy.data.service;

import com.brainy.data.domain.BrainyUser;
import com.brainy.data.domain.Note;
import com.brainy.data.domain.Tag;
import com.brainy.data.repo.NoteRepo;
import com.brainy.data.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepo noteRepository;

    @Autowired
    private BrainyUserService brainyUserService;

    @Autowired
    private TagRepo tagRepository;

    @Autowired
    private TagService tagService;

    // Trova tutti gli appunti di un utente
    public List<Note> getNotesByUser(Long userId) {
        return noteRepository.findByUserId(userId);
    }

    // Trova un appunto specifico per ID (di un utente)
    public Optional<Note> getNoteById(Long noteId, Long userId) {
        return noteRepository.findById(noteId)
                .filter(note -> note.getUser().getId().equals(userId));
    }

    // Crea un nuovo appunto
    public Note createNote(String note, List<String> tagNames, String email) {
        // Associa l'utente
        BrainyUser user = brainyUserService.findUserByEmail(email);
        Note newNote = new Note();
        newNote.setContent(note);
        newNote.setUser(user);

        // Trova o crea i tag
        List<Tag> tags = tagNames.stream()
                .map(name -> tagRepository.findByName(name)
                        .orElseGet(() -> tagRepository.save(new Tag(name))))
                .toList();
        newNote.setTags(tags);

        return noteRepository.save(newNote);
    }

    // Aggiorna un appunto esistente
    public Note updateNote(Long noteId, Note updatedNote, List<String> tagNames, Long userId) {
        return noteRepository.findById(noteId)
                .filter(note -> note.getUser().getId().equals(userId))
                .map(note -> {
                    note.setContent(updatedNote.getContent());

                    // Aggiorna i tag
                    List<Tag> tags = tagNames.stream()
                            .map(name -> tagRepository.findByName(name)
                                    .orElseGet(() -> tagRepository.save(new Tag(name))))
                            .toList();
                    note.setTags(tags);

                    return noteRepository.save(note);
                })
                .orElseThrow(() -> new RuntimeException("Note not found or not authorized"));
    }

    // Elimina un appunto
    public void deleteNote(Long noteId, Long userId) {
        noteRepository.findById(noteId)
                .filter(note -> note.getUser().getId().equals(userId))
                .ifPresent(noteRepository::delete);
    }

}
