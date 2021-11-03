package com.gf.notesapp.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
@Schema(name = "NoteData", description = "Note Data")
public class NoteData {
  @Schema(description = "Note content")
  private String content;
  @NotNull
  @Schema(description = "Note last modification date")
  private Timestamp date;
  @NotNull
  @Schema(description = "Note id")
  private Integer id;
  @Schema(description = "Note tags")
  private List<String> tags;
  @NotEmpty
  @Schema(description = "Note title")
  private String title;
}
