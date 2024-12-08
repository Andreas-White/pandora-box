package intefcaces.generics;

public class CalculatorExceptions extends RuntimeException {

    public CalculatorExceptions() {
        super("No division with 0 in denominator");
    }
}
