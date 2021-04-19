package hw2.tsk4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Task4 {

    public static void main(String[] args) throws Exception {

        File file = new File(".\\resources\\text\\text.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        Stack<String> stack = new Stack<>();
        String line;
        while ((line = br.readLine()) != null) {
            stack.push(line);
        }

        while (!stack.empty()) {
            System.out.printf("%s\n", stack.pop());
        }
    }

}

