import java.io.PrintWriter;
import java.util.Scanner;

public class Task2010 {
    /*
    //Need for testing system only
    public static void main(String[] args) throws Exception {
        Task2010 task = new Task2010();
        task.solve();
    }
     */

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int a = scan.nextInt();
        int b = scan.nextInt();
        int counter = 0;

        do {
            if (a < b) {
                int x = a;
                a = b;
                b = x;
            }
            a -= b;
            ++counter;
        } while ((a > b ? b : a) > 0);

        writer.printf("%d %d", counter, (a > b) ? a : b);
        writer.close();
    }
}
