package com.gf.notesapp.model.dto.inquiry;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * The type Page data used to transport Data Page.
 *
 * @param <T> the type parameter
 */
@Data
@Schema(name = "PageData", description = "Page data used to transport Data Page")
public class PageData<T> {
  /**
   * The Content.
   */
  @Schema(description = "Page Content")
  private List<T> content;
  /**
   * The Has more data.
   */
  @Schema(description = "Page Has more data indicator")
  private boolean hasMoreData;
  @Schema(description = "Total records Count")
  private Long totalCount;
  @Schema(description = "Total Pages")
  private Integer totalPages;
}
