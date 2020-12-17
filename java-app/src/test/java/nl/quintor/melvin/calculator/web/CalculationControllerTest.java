package nl.quintor.melvin.calculator.web;

import nl.quintor.melvin.calculator.domain.Calculation;
import nl.quintor.melvin.calculator.domain.OperationType;
import nl.quintor.melvin.calculator.repository.CalculationRepository;
import nl.quintor.melvin.calculator.service.CalculationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculationControllerTest {

    @Mock
    private CalculationService calculationService;
    @InjectMocks
    private CalculationController calculationController;

    private static final Calculation CALCULATION = Calculation.builder()
            .id(1337L)
            .operationType(OperationType.MULTIPLICATION)
            .numberOne(3)
            .numberTwo(3)
            .result(9d)
            .build();

    @Test
    void getAllCalculationsTest() {
        when(calculationService.findAllCalculations()).thenReturn(Collections.singletonList(CALCULATION));

        final List<Calculation> calculations = calculationController.all();
        assertNotNull(calculations);
        assertEquals(1, calculations.size());
        verify(calculationService, times(1)).findAllCalculations();
    }

    @Test
    void newCalculationTest() {
        when(calculationService.calculate(CALCULATION)).thenReturn(CALCULATION);

        final Calculation calculationResult = calculationController.newCalculation(CALCULATION);

        assertNotNull(calculationResult);
        verify(calculationService, times(1)).calculate(CALCULATION);
    }
}
