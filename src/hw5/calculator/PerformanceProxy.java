package hw5.calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PerformanceProxy implements InvocationHandler {

    private final Object delegate;

    public PerformanceProxy(Object delegate) {
        this.delegate = delegate;
    }

    public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new PerformanceProxy(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            long startTime = System.nanoTime();
            Object out = method.invoke(delegate, args);
            long estimatedTime = System.nanoTime() - startTime;
            if (method.isAnnotationPresent(Metric.class)) {
                System.out.println(String.format("Время работы метода %s: %d (в наносек)",
                        method.getName(), estimatedTime));
            }
            return out;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Access error", e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}


