package com.wit.springCalculator.operations;

import java.math.BigDecimal;

public class Subtract implements MathematicalOperation {

    @Override
    public BigDecimal execute(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }
}
