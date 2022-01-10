package com.wit.springCalculator.operations;

import com.wit.springCalculator.core.exception.MathematicalException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Divide implements MathematicalOperation {

    @Override
    public BigDecimal execute(BigDecimal a, BigDecimal b) {
        if(b.equals(BigDecimal.ZERO))
            throw new MathematicalException("Cannot divide by ZERO");
        return a.divide(b, new MathContext(a.divide(b, new MathContext(50, RoundingMode.CEILING)).precision(),
                RoundingMode.CEILING));
    }


}
