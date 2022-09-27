package pl.koziol.calculator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "history_of_math_operations")
@Entity
public class MathOperation {

    @Id
    private String mathOperation;

    private BigDecimal operationResult;
    private Unit targetUnit;
    private int unitDimension;

    public MathOperation(String mathOperation, BigDecimal operationResult, Unit targetUnit, int unitDimension) {
        this.mathOperation = mathOperation;
        this.operationResult = operationResult;
        this.targetUnit = targetUnit;
        this.unitDimension = unitDimension;
    }
}
