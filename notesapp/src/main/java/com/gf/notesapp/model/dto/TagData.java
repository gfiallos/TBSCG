package com.gf.notesapp.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class TagData {
  @Size(max = 200)
  private String description;
  @NotEmpty
  @Size(min = 1, max = 60)
  private String name;
}
