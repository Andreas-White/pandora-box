package design_patterns.creational.singleton;

public class RuntimeSingletonExample {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        runtime.gc();

        Runtime runtime2 = Runtime.getRuntime();

        if (runtime == runtime2) {
            System.out.printf("Runtime is the same %s:%s", runtime, runtime2);
        }
    }
}
