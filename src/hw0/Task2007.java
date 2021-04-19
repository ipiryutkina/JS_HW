import java.io.PrintWriter;
import java.util.Scanner;

public class Task2007 {
    /*
    //Need for testing system only
    public static void main(String[] args) throws Exception {
        Task2007 task = new Task2007();
        task.solve();
    }
     */

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int n = scan.nextInt(), p, counter = -1;
        do {
            p = n & 1;
            n >>= 1;
            ++counter;
        } while (p == 0);

        writer.print(counter);
        writer.close();
    }
}