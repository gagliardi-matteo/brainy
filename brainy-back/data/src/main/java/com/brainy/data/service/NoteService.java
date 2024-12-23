package com.brainy.data.service;

import com.brainy.data.domain.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    public List<Note> getNotesByUser(Long userId);
    public Optional<Note> getNoteById(Long noteId, Long userId);
    public Note createNote(String note, List<String> tagNames, String userId);
    public Note updateNote(Long noteId, Note updatedNote, List<String> tagNames, Long userId);
    public void deleteNote(Long noteId, Long userId);

}
