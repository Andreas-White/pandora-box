package generics;

import intefcaces.generics.CalculatorDecimals;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> integers = List.of(5,6,89,6,3,1,4);
        List<Double> doubles = List.of(2.0,88.3,4.0);
        System.out.println(GenericCalculator.addLists(integers, doubles));

        System.out.println(GenericCalculator.add(3.2,5));

        List<? extends Number> numbers = List.of(15, 23.4);
        System.out.println(GenericCalculator.addFromList(numbers));
        System.out.println(GenericCalculator.subtrackFromList(numbers));
        System.out.println(GenericCalculator.multiplyFromList(numbers));
        System.out.println(GenericCalculator.divideFromList(numbers));

    }
}
