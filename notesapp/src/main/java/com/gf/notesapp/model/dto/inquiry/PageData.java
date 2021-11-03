package com.gf.notesapp.model.dto.inquiry;

import lombok.Data;

import java.util.List;

/**
 * The type Page data used to transport Data Page.
 *
 * @param <T> the type parameter
 */
@Data
public class PageData<T> {
  /**
   * The Content.
   */
  private List<T> content;
  /**
   * The Has more data.
   */
  private boolean hasMoreData;
  private Long totalCount;
  private Integer totalPages;
}
