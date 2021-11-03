package com.gf.notesapp.logic.advice;

import com.gf.notesapp.logic.exception.NODataFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception Handle of No data found exception controller advice.
 */
@ControllerAdvice
public class NODataFoundExceptionControllerAdvice extends ExceptionControllerAdvice {

  /**
   * No data found exception handler object.
   *
   * @param pException the p exception value
   * @return the object
   */
  @ExceptionHandler(NODataFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Object noDataFoundExceptionHandler(NODataFoundException pException) {
    var data = ExceptionControllerAdvice.prepareData(pException, ExceptionUtils.getRootCause(pException))
        .addData("status", HttpStatus.NOT_FOUND.value())
        .addData("dataType", pException.getType());
    pException.getData()
        .forEach(data::addData);
    return this.prepareResponse(data, HttpStatus.NOT_FOUND);
  }

}
