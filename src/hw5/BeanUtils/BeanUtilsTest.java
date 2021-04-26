package hw5.BeanUtils;

public class BeanUtilsTest {

    public static void main(String[] args) throws Exception {
        Animal yeti = new Animal("Yeti", 100);
        Animal vasia  = new Man("Vasia", 20, "6004 722067");

        System.out.println(yeti.toString());
        System.out.println(vasia.toString());

        BeanUtils.assign(vasia, yeti);

        System.out.println(yeti.toString());
        System.out.println(vasia.toString());

    }

}
