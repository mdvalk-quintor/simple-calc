package nl.quintor.melvin.calculator.service;

import nl.quintor.melvin.calculator.domain.Calculation;
import nl.quintor.melvin.calculator.domain.OperationType;
import nl.quintor.melvin.calculator.domain.SimpleCalculator;
import nl.quintor.melvin.calculator.repository.CalculationRepository;
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
class CalculationServiceTest {

    @Mock
    private SimpleCalculator simpleCalculator;
    @Mock
    private CalculationRepository calculationRepository;
    @InjectMocks
    private CalculationService calculationService;

    private static final Calculation CALCULATION = Calculation.builder()
            .id(1337L)
            .operationType(OperationType.MULTIPLICATION)
            .numberOne(3)
            .numberTwo(3)
            .build();

    @Test
    void findAllCalculationsTest() {
        when(calculationRepository.findAll()).thenReturn(Collections.singletonList(CALCULATION));

        final List<Calculation> calculations = calculationService.findAllCalculations();
        assertNotNull(calculations);
        assertEquals(1, calculations.size());
        verify(calculationRepository, times(1)).findAll();
    }

    @Test
    void calculateTest() {
        when(calculationRepository.save(CALCULATION)).thenReturn(CALCULATION);
        when(simpleCalculator.multiply(3, 3)).thenReturn(9d);

        final Calculation calculationResult = calculationService.calculate(CALCULATION);

        assertNotNull(calculationResult);
        assertEquals(9d, calculationResult.getResult());
        verify(simpleCalculator, times(1)).multiply(3, 3);
        verify(calculationRepository, times(1)).save(CALCULATION);
    }
}
