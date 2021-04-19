import java.io.PrintWriter;
import java.util.Scanner;

public class Task2028 {

    /*
    //for testing system only
    public static void main(String[] args) throws Exception {
        Task2028 task = new Task2028();
        task.solve();
    }
     */

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int n = scan.nextInt();
        int[] cntx = new int[5];

        for (int i = 0; i < n; ++i) {
            ++cntx[scan.nextInt()];
        }

        for (int i = 0; i < 4; ++i) {
            if (cntx[i] > 0)
                writer.printf("%d %d\n", i, cntx[i]);
        }
        if (cntx[4] > 0)
            writer.printf("%d %d", 4, cntx[4]);

        writer.close();
    }
}
