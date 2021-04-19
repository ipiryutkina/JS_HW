import java.io.PrintWriter;
import java.util.Scanner;

public class Task2027 {

    /*
    //for testing system only
    public static void main(String[] args) throws Exception {
        Task2027 task = new Task2027();
        task.solve();
    }
     */

    private void turn(int[] a, int l, int r) {
        int n = (r - l + 1) / 2;
        for (int i = 0; i < n; ++i) {
            int x = a[i + l];
            a[i + l] = a[r - i];
            a[r - i] = x;
        }
    }

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int n = scan.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = scan.nextInt();
        }

        for (int i = 0; i < 2; ++i) {
            int l = scan.nextInt();
            int r = scan.nextInt();
            turn(a, l - 1, r - 1);
        }

        for (int i = 0; i < n - 1; ++i) {
            writer.printf("%d ", a[i]);
        }
        if (n > 0)
            writer.print(a[n - 1]);

        writer.close();
    }
}
