package com.wit.springCalculator.core.exception;

public class MathematicalException extends RuntimeException {
    public MathematicalException() {
    }

    public MathematicalException(String message) {
        super(message);
    }

    public MathematicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public MathematicalException(Throwable cause) {
        super(cause);
    }
}
