package com.gf.notesapp.model.dto.inquiry;

import com.gf.notesapp.model.domain.OrderType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Schema(name = "OrderRequest", description = "Data Order Request")
public class OrderRequest {
  @NotEmpty
  @Schema(description = "Order Field")
  private String name;
  @NotNull
  @Schema(description = "Order type", example = "ASC")
  private OrderType type;
}
