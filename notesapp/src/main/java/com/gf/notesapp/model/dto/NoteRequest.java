package com.gf.notesapp.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Schema(name = "NoteRequest", description = "New Note Request")
public class NoteRequest {
  @Schema(description = "Note content")
  private String content;
  @Schema(description = "Note tags")
  private List<String> tags;
  @NotEmpty
  @Size(min = 1, max = 200)
  @Schema(description = "Note title")
  private String title;
}
