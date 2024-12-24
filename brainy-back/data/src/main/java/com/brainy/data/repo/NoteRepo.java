package com.brainy.data.repo;

import com.brainy.data.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {

    // Trova appunti di un utente specifico
    List<Note> findByUserId(Long userId);

    // Trova appunti che contengono un certo tag
    List<Note> findByTagsName(String tagName);

}
