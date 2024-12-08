package design_patterns.creational.singleton;

public class MyLoggerSingletonExample {

    // makes it thread safe and lazy loaded
    private static class LazyHolder {
        private static final MyLoggerSingletonExample INSTANCE = new MyLoggerSingletonExample();
    }

    private MyLoggerSingletonExample() {}

    public static MyLoggerSingletonExample getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String log(String msg) {
        return "logged: " + msg;
    }

    private static class Test {
        public static void main(String[] args) {
            MyLoggerSingletonExample logger = MyLoggerSingletonExample.getInstance();
            System.out.println(logger.log("Hello World"));

            MyLoggerSingletonExample logger2 = MyLoggerSingletonExample.getInstance();

            if (logger2 == logger) {
                System.out.printf("The logger is the same \n%s \n%s", logger, logger2);
            }
        }
    }
}
