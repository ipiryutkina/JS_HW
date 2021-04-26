package hw5.BeanUtils;

import java.lang.reflect.Method;
import java.util.function.Predicate;

public class BeanUtilsTest {

    public static void main(String[] args) throws Exception {
        Animal yeti = new Animal("Yeti", 100);
        Animal vasia = new Man("Vasia", 20, "6004 722067");

        //System.out.println(yeti.toString());
        //System.out.println(vasia.toString());

        BeanUtils.assign(vasia, yeti);

        //System.out.println(yeti.toString());
        //System.out.println(vasia.toString());

        //print all methods of object
        BeanUtils.printAllMethods(vasia, new Predicate<Method>() {
            @Override
            public boolean test(Method method) {
                return true;
            }
        });

        //print getters
        BeanUtils.printAllMethods(vasia, new Predicate<Method>() {
            @Override
            public boolean test(Method method) {
                return BeanUtils.isGetter(method);
            }
        });
    }




}
