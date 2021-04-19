package hw2.tsk1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

    public static void main(String[] args) throws Exception {

        File file = new File(".\\resources\\text\\text.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        HashSet<String> hs = new HashSet<>();
        Pattern pattern = Pattern.compile("[\\u0410-\\u044f\\u0451]{1,}");
        String line;
        while ((line = br.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                hs.add(line.substring(matcher.start(), matcher.end()).toLowerCase());
            }
        }

        for (String el : hs) {
            System.out.println(el);
        }
    }

}
