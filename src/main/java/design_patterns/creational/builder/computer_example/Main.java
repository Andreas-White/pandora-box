package design_patterns.creational.builder.computer_example;

public class Main {

    public static void main(String[] args) {
        Computer computer1 = new Computer.ComputerBuilder()
                .setDiskSize(HDD.EXTRA_LARGE)
                .setRAM(RAM.EXTRA_LARGE)
                .setHasUsbC(true)
                .setHasGigabitWifi(false)
                .build();

        System.out.println(computer1);

        Computer computer2 = new Computer.ComputerBuilder()
                .setDiskSize(HDD.EXTRA_LARGE)
                .setHasGigabitWifi(false)
                .build();

        System.out.println(computer2);
    }
}
