package com.gf.notesapp.logic.advice;

import com.gf.notesapp.model.dto.ExceptionData;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

/**
 * Exception Handle of Data validation exception controller advice.
 */
@ControllerAdvice
public class ValidationConstraintViolationExceptionControllerAdvice extends ExceptionControllerAdvice {

  /**
   * Data validation exception handler object.
   *
   * @param pException the p exception value
   * @return the object
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ExceptionData> dataValidationExceptionHandler(ConstraintViolationException pException) {
    var root = ExceptionUtils.getRootCause(pException);
    var data = ExceptionControllerAdvice.prepareData(pException, root)
        .addData("status", HttpStatus.BAD_REQUEST.value());
    return this.prepareResponse(data, HttpStatus.BAD_REQUEST);
  }

}
