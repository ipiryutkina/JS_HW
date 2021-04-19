package hw3.tsk1;

import java.util.HashMap;
import java.util.Map;

public class CountMapTest {

    public static void main(String[] args) throws Exception {

        CountMap<Integer> map = new CountMapImpl<>();

        map.add(10);map.add(10);map.add(5);map.add(6);
        map.add(5);map.add(10);map.add(7);

        System.out.println(map.toMap());

        CountMap<Integer> map1 = new CountMapImpl<>();

        map1.add(1);map1.add(10);map1.add(8);map1.add(7);
        map1.add(6);map1.add(5);map1.add(1);

        System.out.println(map1.toMap());

        map.addAll(map1);
        System.out.println(map.toMap());

        int count = map.remove(10);
        System.out.println(count);
        System.out.println(map.toMap());

        Map<Integer, Integer> c = new HashMap<>();
        c.put(1,2);
        c.put(3,4);
        System.out.println(c);
        map.toMap(c);
        System.out.println(c);

    }
}
