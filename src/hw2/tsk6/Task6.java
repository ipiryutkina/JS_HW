package hw2.tsk6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Task6 {

    public static void main(String[] args) throws Exception {

        File file = new File(".\\resources\\text\\text.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<String> array = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            array.add(line);
        }

        Integer nTries = 10;
        Random rand = new Random();
        for (int i = 0; i < nTries; ++i) {
            int index = rand.nextInt(array.size());
            System.out.printf("%d %s\n", index, array.get(index));
        }
    }

}


