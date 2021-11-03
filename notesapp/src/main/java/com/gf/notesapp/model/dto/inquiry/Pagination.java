package com.gf.notesapp.model.dto.inquiry;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Schema(name = "Pagination", description = "Pagination Request Data")
public class Pagination {
  @NotNull
  @Min(0)
  @Schema(description = "Page number", example = "0", defaultValue = "0")
  private Integer pageNumber;
  @NotNull
  @Min(1)
  @Schema(description = "Page Size", example = "10", defaultValue = "10")
  private Integer pageSize;

}
