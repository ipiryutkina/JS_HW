package hw5.calculator;

import java.lang.reflect.Proxy;

public class CalculatorTest {

    public static void main(String[] args) throws Exception {
        /*
        Calculator delegate = new CalculatorImpl();
        Calculator calculator= (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new PerformanceProxy(delegate));
        System.out.println(calculator.calc(3));
        */

        Calculator calculator = (Calculator) PerformanceProxy.newInstance(new CalculatorImpl());
        System.out.println(calculator.calc(3));

    }
}
