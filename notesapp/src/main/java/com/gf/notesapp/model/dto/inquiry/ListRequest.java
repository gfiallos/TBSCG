package com.gf.notesapp.model.dto.inquiry;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Schema(name = "ListRequest", description = "Request for listing data")
public class ListRequest {
  @Valid
  @Schema(description = "Search Request")
  private List<Criteria> criteria;
  @Valid
  @Schema(description = "Sort Request")
  private List<OrderRequest> order;
  @NotNull
  @Valid
  @Schema(description = "Pagination Request")
  private Pagination pagination;

}
