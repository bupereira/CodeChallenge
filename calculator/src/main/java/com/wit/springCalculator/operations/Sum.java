package com.wit.springCalculator.operations;

import java.math.BigDecimal;

public class Sum implements MathematicalOperation {

    @Override
    public BigDecimal execute(BigDecimal x, BigDecimal y) {
        return x.add(y);
    }
}
