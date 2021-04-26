package hw5.calculator;

public class CalculatorImpl implements Calculator {

    @Override
    public int calc(int number) {
        int p = 1;
        for (int i = 2; i <= number; ++i)
            p *= i;
        return p;
    }
}
