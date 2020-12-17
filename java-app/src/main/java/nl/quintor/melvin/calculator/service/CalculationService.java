package nl.quintor.melvin.calculator.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.melvin.calculator.domain.Calculation;
import nl.quintor.melvin.calculator.domain.OperationType;
import nl.quintor.melvin.calculator.domain.SimpleCalculator;
import nl.quintor.melvin.calculator.domain.exception.CalculatorException;
import nl.quintor.melvin.calculator.repository.CalculationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class CalculationService {

    private final SimpleCalculator simpleCalculator;
    private final CalculationRepository calculationRepository;

    private static final String NO_RESULT_MESSAGE = "No calculation found with id %d";
    private static final String UNKNOWN_OPERATION_MESSAGE = "Onbekende operatie bij uitvoeren van de berekening: %s";

    public Calculation calculate(Calculation calculation) {
        BiFunction<Integer, Integer, Double> calculationFunction = getOperation(calculation.getOperationType());
        Double result = calculationFunction.apply(calculation.getNumberOne(), calculation.getNumberTwo());
        calculation.setResult(result);
        return calculationRepository.save(calculation);
    }

    public List<Calculation> findAllCalculations() {
        return calculationRepository.findAll();
    }

    private BiFunction<Integer, Integer, Double> getOperation(OperationType operationType) {
        switch (operationType) {
            case MULTIPLICATION: return simpleCalculator::multiply;
            case ADDITION: return simpleCalculator::add;
            case SUBSTRACTION: return simpleCalculator::subtract;
            case DIVISION: return simpleCalculator::divide;
            default: throw new CalculatorException(String.format(UNKNOWN_OPERATION_MESSAGE, operationType));
        }
    }
}
