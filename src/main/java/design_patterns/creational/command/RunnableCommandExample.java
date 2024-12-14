package design_patterns.creational.command;

public class RunnableCommandExample {

    public static void main(String[] args) {
        Task task1 = new Task(2, 3);
        Task task2 = new Task(5, 6);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }

    private record Task(int num1, int num2) implements Runnable {
        @Override
        public void run() {
            System.out.printf("The sum is: %d\n", (num1 + num2));
        }
    }
}
