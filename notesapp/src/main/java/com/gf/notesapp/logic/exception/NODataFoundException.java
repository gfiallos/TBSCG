package com.gf.notesapp.logic.exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Exception No data found exception used to report tha there is no data.
 */
public class NODataFoundException extends RuntimeException {
  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 4032514244660233089L;
  /**
   * The Type.
   */
  private final String type;

  private final Map<String, Serializable> data;

  /**
   * Instantiates a new No data found exception.
   *
   * @param pType the p type
   */
  public NODataFoundException(String pType) {
    super("NO DATA FOUND " + pType);
    this.type = pType;
    this.data = new HashMap<>();
  }

  public NODataFoundException(String pType, Map<String, Serializable> pData) {
    super("NO DATA FOUND " + pType);
    this.type = pType;
    this.data = pData;
  }

  public Map<String, Serializable> getData() {
    return this.data;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType() {
    return this.type;
  }
}
