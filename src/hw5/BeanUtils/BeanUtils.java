package hw5.BeanUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {
        Map<String, Method> hm = new HashMap<>();
        for (Method m : to.getClass().getMethods())
            if (isSetter(m))
                hm.put(m.getName(), m);

        for (Method getter : from.getClass().getMethods()) {
            if (isGetter(getter)) {
                String setterName = "set" + getter.getName().substring(3);
                Method setter = hm.get(setterName);
                if (setter.getParameterTypes()[0].isInstance(getter.getReturnType()))
                    setter.invoke(to, getter.invoke(from));
            }
        }

    }


    private static boolean isGetter(Method method) {
        if (method == null)
            return false;
        if (!method.getName().startsWith("get"))
            return false;
        if (method.getParameterTypes().length != 0)
            return false;
        if (void.class.equals(method.getReturnType()))
            return false;
        return true;
    }

    private static boolean isSetter(Method method) {
        if (method == null)
            return false;
        if (!method.getName().startsWith("set"))
            return false;
        if (method.getParameterTypes().length != 1)
            return false;
        return true;
    }
}

