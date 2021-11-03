package com.gf.notesapp.logic.services;

import com.gf.notesapp.model.dto.NoteData;
import com.gf.notesapp.model.dto.NoteRequest;
import com.gf.notesapp.model.entities.Note;
import com.gf.notesapp.model.entities.Tag;
import com.gf.notesapp.model.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotesService extends AbstractCRUDService<Note, Integer, NoteData, NoteRequest, NotesRepository> {
  private TagsService tagsService;

  @Override
  protected Integer getIdFromData(NoteData pRequest) {
    return pRequest.getId();
  }

  @Override
  protected Integer getIdFromRequest(NoteRequest pRequest) {
    return null;
  }

  @Override
  protected NoteData mapData(Note pRecord) {
    return new NoteData().setDate(pRecord.getDate())
        .setId(pRecord.getId())
        .setContent(pRecord.getContent())
        .setTitle(pRecord.getTitle())
        .setTags(Optional.ofNullable(pRecord.getTags())
            .map(tags -> tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toList()))
            .orElse(new ArrayList<>()));
  }

  @Override
  protected void mapDataEntity(NoteData pRequest, Note pEntity) {
    pEntity.setContent(pRequest.getContent())
        .setTitle(pRequest.getTitle());
  }

  @Override
  protected Note mapRequest(NoteRequest pRequest) {
    return new Note().setContent(pRequest.getContent())
        .setTitle(pRequest.getTitle())
        .setTags(this.tagsService.getTags(pRequest.getTags()));
  }

  @Override
  protected void prePersist(Note pData, boolean pDelete) {
    if (pDelete) {
      this.repository.deleteNoteReferences(pData.getId());
    } else {
      pData.setDate(new Timestamp(System.currentTimeMillis()));
    }
  }

  @Autowired
  public void setTagsService(TagsService pTagsService) {
    this.tagsService = pTagsService;
  }
}
