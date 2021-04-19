package hw2.tsk2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {

    public static void main(String[] args) throws Exception {

        File file = new File(".\\resources\\text\\text.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        Comparator<String> comp = (String s1, String s2) -> (s1.length() != s2.length() ?
                s1.length() - s2.length() : s1.compareTo(s2));
        TreeSet<String> ts = new TreeSet<>(comp);

        Pattern pattern = Pattern.compile("[\\u0410-\\u044f\\u0451]{1,}");

        String line;
        while ((line = br.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String lword = line.substring(matcher.start(), matcher.end()).toLowerCase();
                ts.add(lword);
            }
        }

        for (String el : ts) {
            System.out.println(el);
        }
    }

}
