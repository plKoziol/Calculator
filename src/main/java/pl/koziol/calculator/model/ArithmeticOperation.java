package pl.koziol.calculator.model;

public enum ArithmeticOperation {
    ADD('+'),
    SUBTRACT('-'),
    DIVIDE('/'),
    MULTIPLY('*');

    public char charArithmeticOperation;


    ArithmeticOperation(char charArithmeticOperation) {
        this.charArithmeticOperation =charArithmeticOperation;
    }
}
