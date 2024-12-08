package intefcaces.generics;

public interface Calculator<T> {
    T add(T value1, T value2);
    T remove(T value1, T value2);
    T multiply(T value1, T value2);
    T divide(T value1, T value2);
}
