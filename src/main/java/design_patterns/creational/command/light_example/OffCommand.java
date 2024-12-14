package design_patterns.creational.command.light_example;

public class OffCommand implements Command {

    private final Light light;

    public OffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
