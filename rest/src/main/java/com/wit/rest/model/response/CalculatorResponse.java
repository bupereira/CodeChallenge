package com.wit.rest.model.response;

import java.math.BigDecimal;

public class CalculatorResponse {
    BigDecimal result;

    public CalculatorResponse() {

    }

    public CalculatorResponse(String result) {
        this.result = new BigDecimal(result);
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
