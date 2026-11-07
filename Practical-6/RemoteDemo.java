interface Switchable {
    void on();
    void off();

    default void toggle() {
        on();
    }
}

class Fan implements Switchable {
    public void on() {
        System.out.println("Fan is ON");
    }

    public void off() {
        System.out.println("Fan is OFF");
    }
}

class Light implements Switchable {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

@FunctionalInterface
interface DevicePermission {
    boolean maySwitchOn(String device, int hour);
}

public class RemoteDemo {
    public static void main(String[] args) {
        Switchable[] devices = {new Fan(), new Light()};

        for (Switchable device : devices) {
            device.toggle();
        }

        DevicePermission permission = new DevicePermission() {
            public boolean maySwitchOn(String device, int hour) {
                return hour >= 6 && hour < 23;
            }
        };

        System.out.println("Fan allowed at 10: "
                + permission.maySwitchOn("Fan", 10));

        DevicePermission lambda =
                (device, hour) -> hour >= 6 && hour < 23;

        System.out.println("Light allowed at 23: "
                + lambda.maySwitchOn("Light", 23));
    }
}
