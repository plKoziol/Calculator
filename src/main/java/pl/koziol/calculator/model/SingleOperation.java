package pl.koziol.calculator.model;

import lombok.Getter;


@Getter
public class SingleOperation {
    private ValueAndUnit firstNumber;
    private ValueAndUnit secondNumber;
    private ArithmeticOperation typeOfOperation;

    public SingleOperation(ValueAndUnit firstNumber, ValueAndUnit secondNumber, ArithmeticOperation typeOfOperation) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.typeOfOperation = typeOfOperation;
    }
}
