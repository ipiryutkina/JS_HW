package hw4.task2;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws Exception {

        TerminalImpl ti = new TerminalImpl("1234");
        ti.withdraw(100);
        ti.put(345);
        ti.put(200);
        ti.withdraw(45);
        ti.withdraw(100);
    }
}
