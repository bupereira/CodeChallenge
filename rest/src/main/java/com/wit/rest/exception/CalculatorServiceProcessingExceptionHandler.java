package com.wit.rest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CalculatorServiceProcessingExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorServiceProcessingExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleInvalidRequest(CalculatorServiceProcessingException e,
                                                          ServletWebRequest request) {
        CSPExceptionDetails cspExceptionDetails = new CSPExceptionDetails(
                e.getHttpStatus().value(),
                e.getAssignedId(),
                e.getMessage(),
                e.getHttpStatus().getReasonPhrase());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        logger.info("Error, status " + e.getHttpStatus() + ", client: " + cspExceptionDetails.getAssignedId() + ", msg: " + e.getMessage());
        return handleExceptionInternal(e, cspExceptionDetails, headers, HttpStatus.BAD_REQUEST, request);
    }

}