package com.gf.notesapp.logic.advice;

import com.gf.notesapp.logic.exception.DataValidationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception Handle of Data validation exception controller advice.
 */
@ControllerAdvice
public class DataValidationExceptionControllerAdvice extends ExceptionControllerAdvice {

  /**
   * Data validation exception handler object.
   *
   * @param pException the p exception value
   * @return the object
   */
  @ExceptionHandler(DataValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object dataValidationExceptionHandler(DataValidationException pException) {
    var root = ExceptionUtils.getRootCause(pException);
    var data = ExceptionControllerAdvice.prepareData(pException, root)
        .addData("field", pException.getField())
        .addData("status", HttpStatus.BAD_REQUEST.value())
        .addData("value", pException.getValue());
    return this.prepareResponse(data, HttpStatus.BAD_REQUEST);
  }

}
