package pl.koziol.calculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.koziol.calculator.model.MathOperation;


public interface MathOperationRepository extends JpaRepository<MathOperation, String> {

}
