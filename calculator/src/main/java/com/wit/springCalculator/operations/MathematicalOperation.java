package com.wit.springCalculator.operations;

import java.math.BigDecimal;

public interface MathematicalOperation {

    BigDecimal execute(BigDecimal a, BigDecimal b);
}
