package hw4.task2;

public class PinValidator {

    private final String pin;

    PinValidator(String pin) {
        this.pin = pin;
    }

    public boolean check(String pin) {
        return this.pin.equals(pin);
    }
}
