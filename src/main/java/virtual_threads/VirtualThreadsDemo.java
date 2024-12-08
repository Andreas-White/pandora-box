package virtual_threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import static java.util.concurrent.StructuredTaskScope.Subtask;

public class VirtualThreadsDemo {

    private static final List<String> NAMES = List.of("Tony", "Anna", "Rick");
    public List<String> getVirtualListOfProducers() {
        List<String> strings = new ArrayList<>();
        Thread.Builder.OfVirtual vt = Thread.ofVirtual();
        vt.start(() -> {
            strings.add("Tony");
            strings.add("Sony");
        });
        return strings;
    }

    public static List<String> getVirtualListOfProducersS() throws InterruptedException {
        List<String> strings = new ArrayList<>();
        Runnable task = () -> {
            strings.add("Tony");
            strings.add("Sony");
        };
        Thread.ofVirtual()
                .name("Virtual")
                .start(task)
                .join();
        return strings;
    }

    public static List<String> getVirtualListStructuredTaskScope() {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var subtasks = getNamesLowerCase().stream()
                    .map(s -> scope.fork(() -> STR."soldier \{s}"))
                    .toList();

            scope.join();
            scope.throwIfFailed();

            return subtasks.stream()
                    .map(Subtask::get)
                    .toList();

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getNamesLowerCase() {
        return NAMES.stream()
                .map(String::toLowerCase)
                .toList();
    }

    public static void main(String[] args) throws InterruptedException {
        getVirtualListStructuredTaskScope().forEach(System.out::println);
    }
}
