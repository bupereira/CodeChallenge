package com.wit.rest.exception;

import org.springframework.http.HttpStatus;

public class CalculatorServiceProcessingException extends RuntimeException {
    private HttpStatus httpStatus;
    private String assignedId;
    private String message;

    public CalculatorServiceProcessingException(HttpStatus httpStatus, String assignedId, String message) {
        this.httpStatus = httpStatus;
        this.assignedId = assignedId;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getAssignedId() {
        return assignedId;
    }

    public String getMessage() {
        return message;
    }
}
