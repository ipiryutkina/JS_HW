package hw2.tsk5;

import java.util.*;

public class Task5 {

    public static void main(String[] args) throws Exception {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        for (String s : new ReverseIterator<String>(list)) {
            System.out.println(s);
        }
    }


}
