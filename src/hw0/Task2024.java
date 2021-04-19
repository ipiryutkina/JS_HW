import java.io.PrintWriter;
import java.util.Scanner;

public class Task2024 {

    /*
    //Used for testing system only
    public static void main(String[] args) throws Exception {
        Task2024 task = new Task2024();
        task.solve();
    }
     */

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        int n = scan.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; ++i) {
            a[i] = scan.nextInt();
        }

        int counter = 0;
        for (int i = 0; i < n / 2; ++i) {
            if (a[i] != a[n - 1 - i])
                ++counter;
        }

        writer.print(counter);
        writer.close();
    }
}
