package com.gf.notesapp.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class NoteRequest {
  private String content;
  private List<String> tags;
  @NotEmpty
  @Size(min = 1, max = 200)
  private String title;
}
