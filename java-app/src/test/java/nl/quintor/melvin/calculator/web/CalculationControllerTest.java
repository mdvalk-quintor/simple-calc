package nl.quintor.melvin.calculator.web;

import nl.quintor.melvin.calculator.domain.Calculation;
import nl.quintor.melvin.calculator.domain.OperationType;
import nl.quintor.melvin.calculator.repository.CalculationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculationControllerTest {

    @Mock
    private CalculationRepository calculationRepository;
    @InjectMocks
    private CalculationController calculationController;

    private static final Calculation CALCULATION = Calculation.builder()
            .id(1337L)
            .operationType(OperationType.MULTIPLICATION)
            .numberOne(new BigDecimal(3))
            .numberTwo(new BigDecimal(3))
            .build();

    @Test
    void getAllCalculationsTest() {
        when(calculationRepository.findAll()).thenReturn(Collections.singletonList(CALCULATION));

        final List<Calculation> calculations = calculationController.all();
        assertNotNull(calculations);
        assertEquals(1, calculations.size());
        verify(calculationRepository, times(1)).findAll();
    }

    @Test
    void saveNewCalculationTest() {
        when(calculationRepository.save(CALCULATION)).thenReturn(CALCULATION);

        final Calculation calculationResult = calculationController.newCalculation(CALCULATION);

        assertNotNull(calculationResult);
        verify(calculationRepository, times(1)).save(CALCULATION);
    }
}
