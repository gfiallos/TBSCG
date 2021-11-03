package com.gf.notesapp.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.util.CastUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Exception data.
 */
@Data
@Schema(name = "ExceptionData", description = "Exception handle information")
public class ExceptionData {
  /**
   * The Data.
   */
  @Schema(description = "Aditional data")
  private Map<String, Object> data = new HashMap<>();
  /**
   * The Http status.
   */
  @Schema(description = "Http status code")
  private Integer httpStatus;
  /**
   * The Message.
   */
  @Schema(description = "Exception message")
  private String message;
  /**
   * The Real.
   */
  @Schema(description = "Real exception")
  private String real;
  /**
   * The Stack trace.
   */
  @Schema(description = "Stack trace")
  private String stackTrace;
  /**
   * The Type.
   */
  @Schema(description = "Exception type")
  private String type;
  /**
   * The User message.
   */
  @Schema(description = "User message")
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
