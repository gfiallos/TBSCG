package com.gf.notesapp.model.dto.inquiry;

import com.gf.notesapp.model.domain.OrderType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrderRequest {
  @NotEmpty
  private String name;
  @NotNull
  private OrderType type;
}
