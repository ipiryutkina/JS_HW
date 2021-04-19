import java.io.PrintWriter;
import java.util.Scanner;

public class Task2038 {

    /*
    //for testing system only
    public static void main(String[] args) throws Exception {
        Task2038 task = new Task2038();
        task.solve();
    }
     */

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        String text = scan.nextLine();
        String[] lis = text.split("[^a-zA-Z]{1,}");

        int max_len = 0;
        for (String ss : lis) {
            if (ss.length() > max_len) {
                max_len = ss.length();
            }
        }

        writer.print(max_len);
        writer.close();
    }
}
