package hw5.calculator;

public interface Calculator {
    /**
     * Расчет факториала числа.
     *
     * @param number
     */
    @Metric
    int calc(int number);
}
