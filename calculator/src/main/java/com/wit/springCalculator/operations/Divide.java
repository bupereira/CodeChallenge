package com.wit.springCalculator.operations;

import com.wit.springCalculator.core.exception.MathematicalException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Divide implements MathematicalOperation {

    @Override
    public BigDecimal execute(BigDecimal x, BigDecimal y) {
        if(y.equals(BigDecimal.ZERO))
            throw new MathematicalException("Cannot divide by ZERO");
        BigDecimal result = x.divide(y, new MathContext(x.divide(y, new MathContext(50, RoundingMode.CEILING)).precision(), RoundingMode.CEILING));
        return result;
    }


}
