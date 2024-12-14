package design_patterns.creational.command.light_example;

public class OnCommand implements Command {

    private final Light light;

    public OnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
