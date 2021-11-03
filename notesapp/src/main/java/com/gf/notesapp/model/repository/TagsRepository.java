package com.gf.notesapp.model.repository;

import com.gf.notesapp.model.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tag, String> {
}
