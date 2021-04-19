import java.io.PrintWriter;
import java.util.Scanner;

public class Task2033 {

    public static void main(String[] args) throws Exception {
        Task2033 task = new Task2033();
        task.solve();
    }
    //[aeiouy]{n,}
    private void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int n = scan.nextInt();
        int [] a = new int[n+1];
        a[0] = 0;
        for(int i = 1; i < n+1; ++i)
            a[i] = scan.nextInt();

        int p = 1;
        for(int i = n; i >= 0; --i) {
            int s = a[i] + p;
            a[i] = s % 10;
            p = s / 10;
            if (p==0)
                break;
        }

        writer.printf("%d\n%s", a[0]==0?n:n+1, a[0]==0?"":(Integer.toString(a[0])+' '));

        for(int i = 1; i < n; ++i)
            writer.printf("%d ", a[i]);
        if (n>0)
            writer.printf("%d",a[n]);
        writer.close();
    }
}