package com.brainy.data.repo;

import com.brainy.data.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepo extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);

    @Query("SELECT u FROM Tag u WHERE u.name in :tagNames")
    List<Tag> findAllByName(@Param("email") List<String> tagNames);
}
