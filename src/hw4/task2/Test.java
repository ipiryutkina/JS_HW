package hw4.task2;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws Exception {

        PinValidator pv = new PinValidator("1234");
        TerminalServer ts = new TerminalServer();
        TerminalImpl ti = new TerminalImpl(ts, pv);

        ti.withdraw(100);
        ti.put(345);
        ti.put(200);
        ti.withdraw(45);
        ti.withdraw(100);
    }
}
