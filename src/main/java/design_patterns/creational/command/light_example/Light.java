package design_patterns.creational.command.light_example;

// receiver
public class Light {

    private boolean isOn;

    public void on() {
        System.out.println("Light on");
    }

    public void off() {
        System.out.println("Light off");
    }

    public boolean isOn() {
        return isOn;
    }

    public void toggle() {
        if (isOn) {
            off();
            isOn = false;
        }
        else {
            on();
            isOn = true;
        }
    }
}
