package com.gf.notesapp.logic.advice;

import com.gf.notesapp.logic.exception.GenericException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception Handle of No data found exception controller advice.
 */
@ControllerAdvice
public class GenericExceptionControllerAdvice extends ExceptionControllerAdvice {

  /**
   * No data found exception handler object.
   *
   * @param pException the p exception value
   * @return the object
   */
  @ExceptionHandler(GenericException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object noDataFoundExceptionHandler(GenericException pException) {
    var data = ExceptionControllerAdvice.prepareData(pException, ExceptionUtils.getRootCause(pException));
    return this.prepareResponse(data, HttpStatus.BAD_REQUEST);
  }

}
