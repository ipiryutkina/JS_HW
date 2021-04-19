import java.io.PrintWriter;
import java.util.Scanner;

public class Task2016 {

    /*
    //Need for testing system only
    public static void main(String[] args) throws Exception {
        Task2016 task = new Task2016();
        task.solve();
    }
     */
    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        int n;
        if (a == 0) {
            n = (b == 0) ? (c == 0 ? -1 : 0) : 1;
        } else {
            double D = b * b - 4 * a * c;
            n = (D > 0) ? 2 : ((D < 0) ? 0 : 1);
        }
        writer.print(n);
        writer.close();
    }
}

