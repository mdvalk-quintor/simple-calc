package nl.quintor.melvin.calculator.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SimpleCalculatorTest {

    @InjectMocks
    private SimpleCalculator simpleCalculator;

    @Test
    void addTest() {
        final double result = simpleCalculator.add(15, 30);
        assertEquals(45d, result);
    }

    @Test
    void subtractTest() {
        final double result = simpleCalculator.subtract(15, 30);
        assertEquals(-15d, result);
    }

    @Test
    void multiplyTest() {
        final double result = simpleCalculator.multiply(15, 30);
        assertEquals(450d, result);
    }

    @Test
    void divideTest() {
        final double result = simpleCalculator.divide(15, 30);
        assertEquals(15d/30, result);
    }
}
