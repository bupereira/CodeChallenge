package com.wit.springCalculator.operations;

import java.math.BigDecimal;

public class Sum implements MathematicalOperation {

    @Override
    public BigDecimal execute(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }
}
