package com.gf.notesapp.model.repository;

import com.gf.notesapp.model.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tag, String> {
  @Modifying
  @Query(value = "delete from tnotetags where tagid =:tag", nativeQuery = true)
  int deleteTagReferences(@Param("tag") String tagId);
}
