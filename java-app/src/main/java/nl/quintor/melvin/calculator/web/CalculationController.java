package nl.quintor.melvin.calculator.web;

import lombok.RequiredArgsConstructor;
import nl.quintor.melvin.calculator.domain.Calculation;
import nl.quintor.melvin.calculator.service.CalculationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
class CalculationController {

    private final CalculationService calculationService;

    @GetMapping("/calculations")
    List<Calculation> all() {
        return calculationService.findAllCalculations();
    }

    @PostMapping("/calculations")
    Calculation newCalculation(@RequestBody Calculation calculation) {
        return calculationService.calculate(calculation);
    }

    @GetMapping("/calculations/{id}")
    Calculation one(@PathVariable Long id) {
        return calculationService.findOneCalculation(id);
    }
}
