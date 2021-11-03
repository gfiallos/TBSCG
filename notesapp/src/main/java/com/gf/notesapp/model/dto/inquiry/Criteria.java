package com.gf.notesapp.model.dto.inquiry;


import com.gf.notesapp.model.domain.Operator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Schema(name = "Criteria", description = "Criteria for searching data")
public class Criteria {
  @NotEmpty
  @Schema(description = "Field to search")
  private String name;
  @Schema(description = "Operator to search", defaultValue = "like")
  private Operator operator;
  @NotEmpty
  @Schema(description = "Value to search")
  private String value;
}
