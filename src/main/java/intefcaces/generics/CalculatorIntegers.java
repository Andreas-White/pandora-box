package intefcaces.generics;

public class CalculatorIntegers implements Calculator<Integer> {
    @Override
    public Integer add(Integer value1, Integer value2) {
        return value1 + value2;
    }

    @Override
    public Integer remove(Integer value1, Integer value2) {
        return value1 - value2;
    }

    @Override
    public Integer multiply(Integer value1, Integer value2) {
        return value1 * value2;
    }

    @Override
    public Integer divide(Integer value1, Integer value2) {
        if (value2 == 0) {
            throw new CalculatorExceptions();
        }
        return value1 / value2;
    }


}
