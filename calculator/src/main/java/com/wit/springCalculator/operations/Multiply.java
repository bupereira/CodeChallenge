package com.wit.springCalculator.operations;

import java.math.BigDecimal;

public class Multiply implements MathematicalOperation {

    @Override
    public BigDecimal execute(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }
}
