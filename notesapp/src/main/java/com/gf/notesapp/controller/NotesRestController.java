package com.gf.notesapp.controller;

import com.gf.notesapp.logic.services.NotesService;
import com.gf.notesapp.model.dto.NoteData;
import com.gf.notesapp.model.dto.NoteRequest;
import com.gf.notesapp.model.entities.Note;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NotesRestController extends AbstractCRUDRestController<Note, Integer, NoteData, NoteRequest, NotesService> {

}
