package hw2.cars;

public class Car {

    private String model, type;

    public Car(String model, String type) {
        this.model = model;
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("{Car: model - %s, type - %s}", model, type);
    }
}
