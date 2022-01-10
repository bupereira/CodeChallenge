package com.wit.rest.exception;

import java.io.Serializable;

public class CSPExceptionDetails {
    private int statusCode;
    private String message;
    private String assignedId;
    private String reasonPhrase;

    public CSPExceptionDetails() {

    }

    public CSPExceptionDetails(int statusCode, String assignedId, String message, String reasonPhrase) {
        this.statusCode = statusCode;
        this.assignedId = assignedId;
        this.message = message;
        this.reasonPhrase = reasonPhrase;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAssignedId() {
        return assignedId;
    }

    public void setAssignedId(String assignedId) {
        this.assignedId = assignedId;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }
}
