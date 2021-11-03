package com.gf.notesapp.logic.exception;

/**
 * Exception Generic exception used to Manage Generic messages.
 */
public class GenericException extends RuntimeException {
  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;


  /**
   * Instantiates a new Generic exception.
   *
   * @param message the message
   * @param cause   the cause
   */
  public GenericException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Instantiates a new Generic exception.
   *
   * @param cause the cause
   */
  public GenericException(Throwable cause) {
    super(cause);
  }

  /**
   * Instantiates a new Generic exception.
   *
   * @param cause the cause
   */
  public GenericException(String cause) {
    super(cause);
  }

}
