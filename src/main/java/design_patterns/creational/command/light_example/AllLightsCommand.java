package design_patterns.creational.command.light_example;

import java.util.List;

public class AllLightsCommand implements Command {

    private final List<Light> lights;

    public AllLightsCommand(List<Light> lights) {
        this.lights = lights;
    }

    @Override
    public void execute() {
        lights.forEach(Light::off);
    }
}
