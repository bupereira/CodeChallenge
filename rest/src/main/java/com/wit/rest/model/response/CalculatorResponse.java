package com.wit.rest.model.response;

import java.math.BigDecimal;

public class CalculatorResponse {
    String assignedID;
    BigDecimal result;

    public CalculatorResponse() {

    }
    public CalculatorResponse(BigDecimal result) {
        this.result = result;
    }

    public CalculatorResponse(String assignedId, String result) {
        this.assignedID = assignedId;
        this.result = new BigDecimal(result);
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public String getAssignedID() {
        return assignedID;
    }

    public void setAssignedID(String assignedID) {
        this.assignedID = assignedID;
    }
}
