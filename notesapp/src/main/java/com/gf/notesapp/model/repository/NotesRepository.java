package com.gf.notesapp.model.repository;

import com.gf.notesapp.model.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Note, Integer> {
  @Modifying
  @Query(value = "delete from tnotetags where noteid =:note", nativeQuery = true)
  void deleteNoteReferences(@Param("note") Integer tagId);
}
