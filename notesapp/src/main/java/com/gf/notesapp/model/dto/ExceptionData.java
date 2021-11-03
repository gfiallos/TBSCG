package com.gf.notesapp.model.dto;

import lombok.Data;
import org.springframework.data.util.CastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Exception data.
 */
@Data
public class ExceptionData {
  /**
   * The Data.
   */
  private Map<String, Object> data = new HashMap<>();
  /**
   * The Http status.
   */
  private Integer httpStatus;
  /**
   * The Message.
   */
  private String message;
  /**
   * The Real.
   */
  private String real;
  /**
   * The Stack trace.
   */
  private String stackTrace;
  /**
   * The Type.
   */
  private String type;
  /**
   * The User message.
   */
  private String userMessage;

  /**
   * Add data exception data.
   *
   * @param pKey   the key value
   * @param pValue the value value
   * @return the exception data
   */
  public ExceptionData addData(String pKey, Object pValue) {
    if (pValue instanceof Map) {
      this.data.putAll(CastUtils.cast(pValue));
    } else {
      this.data.put(pKey, pValue);
    }
    return this;
  }
}
