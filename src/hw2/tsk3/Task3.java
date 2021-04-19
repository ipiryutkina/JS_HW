package hw2.tsk3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    public static void main(String[] args) throws Exception {

        File file = new File(".\\resources\\text\\text.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        HashMap<String, Integer> counter = new HashMap<>();
        Pattern pattern = Pattern.compile("[\\u0410-\\u044f\\u0451]{1,}");

        String line;
        while ((line = br.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String lword = line.substring(matcher.start(), matcher.end()).toLowerCase();
                Integer count = counter.get(lword);
                counter.put(lword, count == null ? 1 : count + 1);
            }
        }

        Iterator<Map.Entry<String, Integer>> it = counter.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = it.next();
            System.out.printf("\t%s: %d\n", pair.getKey(), pair.getValue());
        }

    }

}
