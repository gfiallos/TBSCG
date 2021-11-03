package com.gf.notesapp.logic.exception;

/**
 * Exception Data already accepted exception used to mark tha the data is already registered.
 */
public class DataAlreadyAccepedException extends RuntimeException {
  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 4032514244660233089L;
  /**
   * The data Type.
   */
  private final String type;

  /**
   * Instantiates a new Data already acceped exception.
   *
   * @param pType the type of the Already Registered Data
   */
  public DataAlreadyAccepedException(String pType) {
    super("DATA ALREADY ACCEPTED " + pType);
    this.type = pType;
  }

  /**
   * Gets data type.
   *
   * @return the type
   */
  public String getType() {
    return this.type;
  }
}
