package com.incubyte.assessment;

public class StringCalculatorException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public StringCalculatorException() {
        super();
    }

    public StringCalculatorException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public StringCalculatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public StringCalculatorException(String message) {
        super(message);
    }

    public StringCalculatorException(Throwable cause) {
        super(cause);
    }

}
