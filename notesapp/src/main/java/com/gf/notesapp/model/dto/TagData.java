package com.gf.notesapp.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Schema(name = "TagData", description = "Tag data")
public class TagData {
  @Size(max = 200)
  @Schema(description = "Tag Description")
  private String description;
  @NotEmpty
  @Size(min = 1, max = 60)
  @Schema(description = "Tag name", example = "business")
  private String name;
}
