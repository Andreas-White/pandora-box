package design_patterns.creational.command.light_example;

public class ToggleCommand implements Command {

    private final Light light;

    public ToggleCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.toggle();
    }
}
