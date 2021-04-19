import java.io.PrintWriter;
import java.util.Scanner;

public class Task2021 {
    /*
    //for testing system only
    public static void main(String[] args) throws Exception {
        Task2021 task = new Task2021();
        task.solve();
    }
     */
    private void step(int[] a) {

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] > max)
                max = a[i];
        }
        for (int i = 0; i < a.length; ++i)
            if (a[i] == max)
                a[i] /= 2;
    }

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int n = scan.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = scan.nextInt();
        }

        for (int i = 0; i < 2; ++i)
            step(a);

        for (int i = 0; i < n - 1; ++i) {
            writer.printf("%d ", a[i]);
        }
        if (n > 0)
            writer.print(a[n - 1]);

        writer.close();
    }
}
