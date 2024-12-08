package project_reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ReactorSchedulers {


    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> singleProducer().subscribe(index -> {
            System.out.printf("Received index: %d by thread: %s\n", index, Thread.currentThread().getName());
        });

        Thread t1 = new Thread(runnable, "thread1");
        Thread t2 = new Thread(runnable, "thread2");

        System.out.printf("Program thread: %s started\n", Thread.currentThread().getName());

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private static Flux<Integer> simpleProducer() {
        return Flux.range(1, 6)
                .map(index -> {
                    System.out.printf("Produced index: %d by thread: %s\n", index, Thread.currentThread().getName());
                    return index;
                });
    }

    private static Flux<Integer> immediateProducer() {
        return Flux.range(1, 6)
                .publishOn(Schedulers.immediate())
                .map(index -> {
                    System.out.printf("Produced index: %d by thread: %s\n", index, Thread.currentThread().getName());
                    return index;
                });
    }

    private static Flux<Integer> singleProducer() {
        return Flux.range(1, 6)
                .publishOn(Schedulers.newSingle("new single thread"))
                .map(index -> {
                    System.out.printf("Produced index: %d by thread: %s\n", index, Thread.currentThread().getName());
                    return index;
                });
    }

    private static Flux<Integer> boundElasticProducer() {
        return Flux.range(1, 6)
                .publishOn(Schedulers.newBoundedElastic(5, 10, "new bound elastic thread"))
                .map(index -> {
                    System.out.printf("Produced index: %d by thread: %s\n", index, Thread.currentThread().getName());
                    return index;
                });
    }

    private static Flux<Integer> parallelProducer() {
        return Flux.range(1, 6)
                .publishOn(Schedulers.newParallel("new parallel thread"))
                .map(index -> {
                    System.out.printf("Produced index: %d by thread: %s\n", index, Thread.currentThread().getName());
                    return index;
                });
    }
}
