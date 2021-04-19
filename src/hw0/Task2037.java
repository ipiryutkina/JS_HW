import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringJoiner;

public class Task2037 {
    /*
    //for testing system only
    public static void main(String[] args) throws Exception {
        Task2037 task = new Task2037();
        task.solve();
    }
    */

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        String text = scan.nextLine();
        int n = scan.nextInt();

        StringJoiner stjr = new StringJoiner(",");
        int start = 0;
        int found = text.indexOf(',');
        while (found >= 0) {
            //writer.printf("%d %d",start,found);
            String subs = text.substring(start, found);
            if (subs.length() >= n) {
                stjr.add(subs);
            }
            start = found + 1;
            found = text.indexOf(',', found + 1);
        }
        String subs = text.substring(start, text.length());
        if (subs.length() >= n) {
            stjr.add(subs);
        }

        writer.print(stjr.toString());
        writer.close();
    }
}

