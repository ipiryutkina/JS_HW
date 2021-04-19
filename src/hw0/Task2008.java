import java.io.PrintWriter;
import java.util.Scanner;

public class Task2008 {
    /*
    Need for testing system only
    public static void main(String[] args) throws Exception {
        Task2008 task = new Task2008();
        task.solve();
    }
     */
    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int n = scan.nextInt();
        int max_weight = scan.nextInt(), s = 0, counter = 0;
        for (int i = 0; i < n; ++i) {
            int curr_weight = scan.nextInt();
            if (s + curr_weight <= max_weight) {
                s += curr_weight;
                counter += 1;
            }
        }

        writer.printf("%d %d", counter, s);
        writer.close();
    }
}