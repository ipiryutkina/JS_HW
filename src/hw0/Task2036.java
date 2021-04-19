import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Task2036 {

    /*
    //for testing system only
    public static void main(String[] args) throws Exception {
        Task2036 task = new Task2036();
        task.solve();
    }
     */

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        Pattern p = Pattern.compile("[aeiouy]{3}");

        int n = scan.nextInt();
        String s1 = scan.nextLine();

        StringJoiner stjr = new StringJoiner("\n");

        for (int i = 0; i < n; ++i) {
            String s = scan.nextLine();
            Matcher m = p.matcher(s);
            if (!m.find()) {
                stjr.add(s);
            }
        }
        writer.print(stjr.toString());
        writer.close();
    }
}