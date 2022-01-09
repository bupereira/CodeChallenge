package com.wit.springCalculator.operations;

import java.math.BigDecimal;

public class Multiply implements MathematicalOperation {

    @Override
    public BigDecimal execute(BigDecimal x, BigDecimal y) {
        return x.multiply(y);
    }
}
