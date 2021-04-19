import java.io.PrintWriter;
import java.util.Scanner;

public class Task2032 {

    public static void main(String[] args) throws Exception {
        Task2032 task = new Task2032();
        task.solve();
    }

    private void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int na = scan.nextInt();
        int [] a = new int[na];
        for (int i = 0; i < na; ++i) {
            a[i] = scan.nextInt();
        }

        int nb = scan.nextInt();
        int [] b = new int[nb];
        for (int i = 0; i < nb; ++i) {
            b[i] = scan.nextInt();
        }

        if (na != nb){
            writer.print((na<nb)?-1:1);
        }
        else{
            int i = na-1;
            while(i >= 0){
                if (a[i]!=b[i]){
                    writer.print((a[i]<b[i])?-1:1);
                    break;
                }
                --i;
            }
            if (i<0){
                writer.print(0);
            }
        }
        writer.close();
    }
}
