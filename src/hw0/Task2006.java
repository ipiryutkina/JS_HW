import java.io.PrintWriter;
import java.util.Scanner;

public class Task2006 {
    /*
    //Need for testing system only
    public static void main(String[] args) throws Exception {
        Task2006 task = new Task2006();
        task.solve();
    }
     */
    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int n = scan.nextInt();
        int foots = n / 36;
        n -= foots * 36;//n%36
        int inches = n / 3;
        n -= inches * 3;

        writer.printf("%d %d", foots, inches + (n < 2 ? 0 : 1));
        writer.close();
    }
}
