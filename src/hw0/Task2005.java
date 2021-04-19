import java.io.PrintWriter;
import java.util.Scanner;

public class Task2005 {
    /*
    //Need for testing system only
    public static void main(String[] args) throws Exception {
            Task2005 task = new Task2005();
            task.solve();
    }
    */
    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int n = scan.nextInt();
        int minimum = Integer.MAX_VALUE, min_idx = -1;
        for (int i = 1; i <= n; ++i) {
            int current = scan.nextInt();
            if (minimum > current) {
                minimum = current;
                min_idx = i;
            }
        }

        writer.print(min_idx);
        writer.close();
    }
}
