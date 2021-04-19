import java.io.PrintWriter;
import java.util.Scanner;

public class Task2004 {
    /*
    //Need for testing system only
    public static void main(String[] args) throws Exception {
        Task2004 task = new Task2004();
        task.solve();
    }
    */
    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        int n = scan.nextInt();
        writer.print(((n & 3) == 0 && n % 100 > 0 || n % 400 == 0) ? 1 : 0);
        writer.close();
    }
}