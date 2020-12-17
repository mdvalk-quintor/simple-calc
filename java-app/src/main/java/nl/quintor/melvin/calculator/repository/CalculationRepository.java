package nl.quintor.melvin.calculator.repository;

import nl.quintor.melvin.calculator.domain.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
}
