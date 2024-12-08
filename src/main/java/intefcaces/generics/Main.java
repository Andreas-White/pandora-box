package intefcaces.generics;

public class Main {

    public static void main(String[] args) {
        Calculator<Integer> calculatorIntegers = new CalculatorIntegers();
        Calculator<Double>  calculatorDouble = new CalculatorDecimals();

        try {
            System.out.println(calculatorIntegers.divide(5,0));
            System.out.println(calculatorDouble.divide(5.0,.0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(calculatorIntegers.add(2,3));
        System.out.println(calculatorIntegers.remove(0,5));
        System.out.println(calculatorIntegers.multiply(0,5));
        System.out.println(calculatorIntegers.divide(0,5));

        System.out.println(calculatorDouble.add(2.0,3.2));
        System.out.println(calculatorDouble.remove(.0,.5));
        System.out.println(calculatorDouble.multiply(2.0,5.5));
    }
}
