package com.gf.notesapp.logic.exception;

/**
 * Exception Data validation exception used to report an error of data validation.
 */
public class DataValidationException extends RuntimeException {
  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 4032514244660233089L;
  /**
   * The Field.
   */
  private final String field;
  /**
   * The Value.
   */
  private final String value;

  /**
   * Instantiates a new Data validation exception.
   *
   * @param pMessage the p message
   * @param pField   the p field
   * @param pValue   the p value
   */
  public DataValidationException(String pMessage, String pField, String pValue) {
    super(pMessage);
    this.field = pField;
    this.value = pValue;
  }

  public DataValidationException(String pMessage, Throwable pCause) {
    super(pMessage, pCause);
    this.field = "INTERNAL";
    this.value = pMessage;
  }

  /**
   * Gets field.
   *
   * @return the field
   */
  public String getField() {
    return this.field;
  }

  /**
   * Gets value.
   *
   * @return the value
   */
  public String getValue() {
    return this.value;
  }
}
