package intefcaces.generics;

public class CalculatorDecimals implements Calculator<Double> {
    @Override
    public Double add(Double value1, Double value2) {
        return value1 + value2;
    }

    @Override
    public Double remove(Double value1, Double value2) {
        return value1 - value2;
    }

    @Override
    public Double multiply(Double value1, Double value2) {
        return value1 * value2;
    }

    @Override
    public Double divide(Double value1, Double value2) {
        if (value2 == 0) {
            throw new CalculatorExceptions();
        }
        return value1 / value2;
    }
}
