package design_patterns.creational.builder.computer_example;

public class Computer {

    public static class ComputerBuilder {
        private HDD diskSize;
        private RAM ramSize;
        private boolean hasUsbC;
        private boolean hasGigabitWifi;

        public ComputerBuilder() {}

        public Computer build() {
            return new Computer(this);
        }

        public ComputerBuilder setDiskSize(HDD diskSize) {
            this.diskSize = diskSize;
            return this;
        }

        public ComputerBuilder setRAM(RAM ramSize) {
            this.ramSize = ramSize;
            return this;
        }

        public ComputerBuilder setHasUsbC(boolean hasUsbC) {
            this.hasUsbC = hasUsbC;
            return this;
        }

        public ComputerBuilder setHasGigabitWifi(boolean hasGigabitWifi) {
            this.hasGigabitWifi = hasGigabitWifi;
            return this;
        }
    }

    private final HDD diskSize;
    private final RAM ramSize;
    private final boolean hasUsbC;
    private final boolean hasGigabitWifi;

    public Computer(ComputerBuilder builder) {
        this.diskSize = builder.diskSize;
        this.ramSize = builder.ramSize;
        this.hasUsbC = builder.hasUsbC;
        this.hasGigabitWifi = builder.hasGigabitWifi;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "diskSize=" + diskSize +
                ", ramSize=" + ramSize +
                ", hasUsbC=" + hasUsbC +
                ", hasGigabitWifi=" + hasGigabitWifi +
                '}';
    }
}
