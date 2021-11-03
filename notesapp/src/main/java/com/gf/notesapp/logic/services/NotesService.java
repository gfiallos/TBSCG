package com.gf.notesapp.logic.services;

import com.gf.notesapp.model.dto.NoteData;
import com.gf.notesapp.model.dto.NoteRequest;
import com.gf.notesapp.model.entities.Note;
import com.gf.notesapp.model.repository.NotesRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class NotesService extends AbstractCRUDService<Note, Integer, NoteData, NoteRequest, NotesRepository> {

  @Override
  protected Integer getIdFromData(NoteData pRequest) {
    return pRequest.getId();
  }

  @Override
  protected Integer getIdFromRequest(NoteRequest pRequest) {
    return null;
  }

  @Override
  protected void mapDataEntity(NoteData pRequest, Note pEntity) {
    pEntity.setContent(pRequest.getContent())
        .setDate(new Timestamp(System.currentTimeMillis()))
        .setTitle(pRequest.getTitle());

  }
}
