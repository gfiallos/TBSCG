package com.gf.notesapp.model.dto.inquiry;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Data
public class ListRequest {
  @Valid
  private List<Criteria> criteria;
  @Valid
  private List<OrderRequest> order;
  @NotNull
  @Valid
  private Pagination pagination;


  public String findCriteria(String pName) {
    return this.getCriteria()
        .stream()
        .filter(c1 -> c1.getName()
            .equals(pName))
        .findFirst()
        .map(Criteria::getValue)
        .orElse(null);
  }


  public boolean hasCriteria(String pName) {
    return Optional.ofNullable(this.getCriteria())
        .map(c -> c.stream()
            .anyMatch(c1 -> c1.getName()
                .equals(pName)))
        .orElse(false);
  }
}
