package com.gf.notesapp.model.dto.inquiry;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Pagination {
  @NotNull
  @Min(0)
  private Integer pageNumber;
  @NotNull
  @Min(1)
  private Integer pageSize;

}
