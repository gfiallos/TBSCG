package com.gf.notesapp.logic.advice;

import com.gf.notesapp.logic.exception.DataAlreadyAccepedException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception Handle of Data already accepted exception controller advice.
 */
@ControllerAdvice
public class DataAlreadyAcceptedExceptionControllerAdvice extends ExceptionControllerAdvice {

  /**
   * Data already acceped exception handler object.
   *
   * @param pException the p exception value
   * @return the object
   */
  @ExceptionHandler(DataAlreadyAccepedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object dataAlreadyAccepedExceptionHandler(DataAlreadyAccepedException pException) {
    var data = ExceptionControllerAdvice.prepareData(pException, ExceptionUtils.getRootCause(pException))
        .addData("status", HttpStatus.BAD_REQUEST.value())
        .addData("dataType", pException.getType());
    return this.prepareResponse(data, HttpStatus.BAD_REQUEST);
  }

}
