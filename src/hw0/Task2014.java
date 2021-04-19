import java.io.PrintWriter;
import java.util.Scanner;

public class Task2014 {

    public static void main(String[] args) throws Exception {
        Task2014 task = new Task2014();
        task.solve();
    }

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int a = scan.nextInt();
        int b = scan.nextInt();
        int counter = 0, x;
        if (a<b){
            x = a;
            a = b;
            b = x;
        }

        while(b>0){
            a -= b;
            ++counter;
            if (a < b) {
                x = a;
                a = b;
                b = x;
            }
        }

        writer.printf("%d %d", counter, a);
        writer.close();
    }
}

