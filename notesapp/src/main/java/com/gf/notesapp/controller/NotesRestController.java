package com.gf.notesapp.controller;

import com.gf.notesapp.logic.services.NotesService;
import com.gf.notesapp.model.dto.NoteData;
import com.gf.notesapp.model.dto.NoteRequest;
import com.gf.notesapp.model.entities.Note;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
@Tag(name = "Notes", description = "Notes operations")
public class NotesRestController extends AbstractCRUDRestController<Note, Integer, NoteData, NoteRequest, NotesService> {

}
