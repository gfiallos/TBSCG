package com.gf.notesapp.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
public class NoteData {
  private String content;
  @NotNull
  private Timestamp date;
  @NotNull
  private Integer id;
  private List<String> tags;
  @NotEmpty
  private String title;
}
