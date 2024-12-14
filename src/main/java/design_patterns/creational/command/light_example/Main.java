package design_patterns.creational.command.light_example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Light light1 = new Light();
        Light light2 = new Light();
        Light light3 = new Light();
        Switch switcher = new Switch();

        Command onCommand = new OnCommand(light1);
        Command offCommand = new OffCommand(light1);
        Command toggleCommand = new ToggleCommand(light1);

        switcher.storeAndExecute(onCommand);
        switcher.storeAndExecute(offCommand);
        switcher.storeAndExecute(toggleCommand);
        switcher.storeAndExecute(toggleCommand);

        List<Light> lights = new ArrayList<>();
        lights.add(light1);
        lights.add(light2);
        lights.add(light3);

        Command allLightsCommand = new AllLightsCommand(lights);
        switcher.storeAndExecute(allLightsCommand);
    }
}
