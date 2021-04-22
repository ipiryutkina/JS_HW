package hw1.task;

public class TemperatureConverter {

    public static double celsiusToFahrenheit(double ct) {
        return ct * 1.8 + 32.0;
    }

    public static double celsiusToKelvin(double ct) {
        return ct + 273.15;
    }

    public static double celsiusToRankin(double ct) {
        return ct * 1.8 + 491.67;
    }

    public static double celsiusToNewton(double ct) {
        return ct / 3.03;
    }

    public static double celsiusToDelil(double ct) {
        return ct * 1.5 - 100.0;
    }

    public static double celsiusToReomur(double ct) {
        return 0.8 * ct;
    }

}
