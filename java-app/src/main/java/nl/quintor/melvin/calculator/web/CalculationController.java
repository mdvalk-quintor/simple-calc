package nl.quintor.melvin.calculator.web;

import lombok.RequiredArgsConstructor;
import nl.quintor.melvin.calculator.domain.Calculation;
import nl.quintor.melvin.calculator.domain.exception.CalculatorException;
import nl.quintor.melvin.calculator.repository.CalculationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
class CalculationController {

    private final CalculationRepository calculationRepository;

    private static final String NO_RESULT_MESSAGE = "No calculation found with id %d";

    @GetMapping("/calculations")
    List<Calculation> all() {
        return calculationRepository.findAll();
    }

    @PostMapping("/calculations")
    Calculation newCalculation(@RequestBody Calculation newCalculation) {
        return calculationRepository.save(newCalculation);
    }

    @GetMapping("/calculations/{id}")
    Calculation one(@PathVariable Long id) {
        return calculationRepository.findById(id)
                .orElseThrow(() -> new CalculatorException(String.format(NO_RESULT_MESSAGE, id)));
    }
}
