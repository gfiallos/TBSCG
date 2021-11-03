package com.gf.notesapp.controller;

import com.gf.notesapp.logic.services.TagsService;
import com.gf.notesapp.model.dto.TagData;
import com.gf.notesapp.model.entities.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tags", description = "Tags Operations")
public class TagsRestController extends AbstractCRUDRestController<Tag, String, TagData, TagData, TagsService> {
}
