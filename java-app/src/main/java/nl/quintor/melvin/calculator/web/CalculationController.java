package nl.quintor.melvin.calculator.web;

import nl.quintor.melvin.calculator.domain.Calculation;
import nl.quintor.melvin.calculator.domain.OperationType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
class CalculationController {

    @GetMapping("/calculations")
    List<Calculation> all() {
        Calculation calculation = new Calculation(0L, OperationType.ADDITION, new BigDecimal(2), new BigDecimal(2));
        ArrayList<Calculation> calcs = new ArrayList<>();
        calcs.add(calculation);
        return calcs;
    }

    @PostMapping("/calculations")
    Calculation newCalculation(@RequestBody Calculation newCalculation) {
        return newCalculation;
    }

    @GetMapping("/calculations/{id}")
    Calculation one(@PathVariable Long id) {
        return new Calculation();
    }

    @DeleteMapping("/calculations/{id}")
    void deleteCalculation(@PathVariable Long id) {
    }
}
