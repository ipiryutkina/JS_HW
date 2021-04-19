import java.io.PrintWriter;
import java.util.Scanner;

public class Task2009 {
    /*
    //Need for testing system only
    public static void main(String[] args) throws Exception {
        Task2009 task = new Task2009();
        task.solve();
    }
    */

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int n = scan.nextInt(), power_of_two = 1, s = 0;
        for (int i = 1; i <= n; ++i) {
            int curr_num = scan.nextInt();
            if (i == power_of_two) {
                s += curr_num;
                power_of_two <<= 1;
            }
        }

        writer.print(s);
        writer.close();
    }
}
