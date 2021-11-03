package com.gf.notesapp.model.dto.inquiry;


import com.gf.notesapp.model.domain.Operator;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Criteria {
  @NotEmpty
  private String name;
  private Operator operator;
  @NotEmpty
  private String value;
}
