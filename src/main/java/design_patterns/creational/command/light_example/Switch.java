package design_patterns.creational.command.light_example;

// invoker
public class Switch {

    public void storeAndExecute(Command command) {
        command.execute();
    }
}
