package nl.quintor.melvin.calculator.domain;

import org.springframework.stereotype.Component;

@Component
public class SimpleCalculator {

    public double add(int numberOne, int numberTwo) {
        return (double)numberOne + numberTwo;
    }

    public double subtract(int numberOne, int numberTwo) {
        return (double)numberOne - numberTwo;
    }

    public double multiply(int numberOne, int numberTwo) {
        return (double)numberOne * numberTwo;
    }

    public double divide(int numberOne, int numberTwo) {
        return (double)numberOne / numberTwo;
    }
}
