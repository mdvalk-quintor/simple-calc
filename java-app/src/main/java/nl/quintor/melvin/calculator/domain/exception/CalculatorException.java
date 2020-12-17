package nl.quintor.melvin.calculator.domain.exception;

public class CalculatorException extends RuntimeException {

    public CalculatorException() {

    }

    public CalculatorException(String message) {
        super(message);
    }
}