package com.gf.notesapp.logic.advice;

import com.gf.notesapp.model.dto.ExceptionData;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;


/**
 * Exception Handle of Exception controller advice.
 */
public class ExceptionControllerAdvice {
  /**
   * The constant MAX_STACK_LINES.
   */
  public static final int MAX_STACK_LINES = 4;
  /**
   * The constant BASIC_PKG.
   */
  private static final String BASIC_PKG = "com.gf";
  /**
   * The constant LOG.
   */
  private static final Logger LOG = LogManager.getLogger(ExceptionControllerAdvice.class);

  /**
   * Prepare data exception data.
   *
   * @param pThrowable the p throwable value
   * @param pOrigin    the p origin value
   * @return the exception data
   */
  public static ExceptionData prepareData(Throwable pThrowable, Throwable pOrigin) {
    var realException = Optional.ofNullable(pOrigin)
        .orElse(pThrowable);
    ExceptionControllerAdvice.LOG.info("{}: {}",
        pThrowable.getClass()
            .getName(),
        ExceptionUtils.getMessage(realException),
        realException);
    var data = new ExceptionData()
        .setMessage(ExceptionUtils.getMessage(realException))
        .setType(realException.getClass()
            .getName())
        .setUserMessage(ExceptionUtils.getMessage(realException));
    var stackTrace = new StringBuilder();
    Arrays.stream(ExceptionUtils.getStackFrames(realException))
        .filter(p -> p.contains(ExceptionControllerAdvice.BASIC_PKG))
        .limit(1)
        .forEach(real -> data.setReal(real.substring(real.indexOf(ExceptionControllerAdvice.BASIC_PKG))));
    Arrays.stream(ExceptionUtils.getStackFrames(pThrowable))
        .limit(ExceptionControllerAdvice.MAX_STACK_LINES)
        .map(line -> line + "\n")
        .forEach(stackTrace::append);
    return data.setStackTrace(stackTrace.toString())
        .addData("exceptionClass",
            pThrowable.getClass()
                .getName());
  }

  protected ResponseEntity<Object> manageCommonExceptionResponseEntity(Exception pException, HttpStatus pStatus) {
    var data = ExceptionControllerAdvice.prepareData(pException, ExceptionUtils.getRootCause(pException));
    return this.prepareResponse(data, pStatus);
  }

  /**
   * Prepare response response entity.
   *
   * @param pMap    the p map value
   * @param pStatus the p status value
   * @return the response entity
   */
  protected ResponseEntity<Object> prepareResponse(ExceptionData pMap, HttpStatus pStatus) {
    pMap.setHttpStatus(pStatus.value());
    return new ResponseEntity<>(pMap, pStatus);
  }

}
