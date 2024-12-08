package data_structures_and_algorithm_analysis.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GenericCalculator<T extends Comparable<T>> implements Comparable<GenericCalculator<T>>{

    private final T value;

    public GenericCalculator(T value) {
        this.value = value;
    }

    @Override
    public int compareTo(GenericCalculator<T> o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return STR."GenericCalculator{value=\{value}\{'}'}";
    }

    public <M> int calcValue(M calc1, M calc2) {
        return calc1.equals(calc2) ? 1 : 0;
    }

    public static void main(String[] args) {
        GenericCalculator<String> genericCalculator1 = new GenericCalculator<>("Bob");
        GenericCalculator<String> genericCalculator2 = new GenericCalculator<>("Alice");
        GenericCalculator<String> genericCalculator3 = new GenericCalculator<>("Dan");
        GenericCalculator<String> genericCalculator4 = new GenericCalculator<>("Elizabeth");
        GenericCalculator<String> genericCalculator5 = new GenericCalculator<>("Carl");
        List<GenericCalculator<String>> list =
                new ArrayList<>(List.of(genericCalculator1, genericCalculator2, genericCalculator3, genericCalculator4, genericCalculator5));

        Collections.sort(list);

        list.forEach(System.out::println);

        System.out.println(genericCalculator1.calcValue("alice", "alice"));
        System.out.println(genericCalculator1.calcValue(1, 2));
        System.out.println(genericCalculator1.calcValue(genericCalculator2, genericCalculator3));

    }
}
