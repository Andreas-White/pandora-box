package generics;

import intefcaces.generics.CalculatorExceptions;

import java.util.ArrayList;
import java.util.List;

public class GenericCalculator {

    public static int addLists(List<? extends Number> numbers1, List<? extends Number> numbers2) {
        if (numbers1.isEmpty() || numbers2.isEmpty()) {
            return 0;
        }

        int total = 0;

        List<Number> mergedList = new ArrayList<>();
        mergedList.addAll(numbers1);
        mergedList.addAll(numbers2);

        for (Number number : mergedList) {
            if (number != null) {
                total += number.intValue();
            }
        }

        return total;
    }

    public static int addFromList(List<? extends Number> numbers) {
        int value1 = numbers.get(0).intValue();
        int value2 = numbers.get(1).intValue();

        return value1 + value2;

    }

    public static int subtrackFromList(List<? extends Number> numbers) {
        int value1 = numbers.get(0).intValue();
        int value2 = numbers.get(1).intValue();

        return value1 - value2;
    }


    public static int multiplyFromList(List<? extends Number> numbers) {
        int value1 = numbers.get(0).intValue();
        int value2 = numbers.get(1).intValue();

        return value1 * value2;

    }

    public static int divideFromList(List<? extends Number> numbers) {
        int value1 = numbers.get(0).intValue();
        int value2 = numbers.get(1).intValue();

        if (value2 == 0) {
            throw new CalculatorExceptions();
        }
        return value1 / value2;

    }
    public static int add(Number number1, Number number2) {
        return number1.intValue() + number2.intValue();
    }
}
