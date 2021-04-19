import java.io.PrintWriter;
import java.util.Scanner;

public class Task2012 {

    public static void main(String[] args) throws Exception {
        Task2012 task = new Task2012();
        task.solve();
    }

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int x = scan.nextInt();
        int y = scan.nextInt();
        int a = scan.nextInt();
        int b = scan.nextInt();

        int l = Math.max(a, b);
        boolean res = (l==Math.max(x,y) && Math.min(x,y)+Math.min(a,b)==l);
        writer.print(res?"YES":"NO");
        writer.close();
    }
}
