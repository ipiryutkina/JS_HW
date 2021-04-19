import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;

public class Task2023 {

    public static void main(String[] args) throws Exception {
        Task2023 task = new Task2023();
        task.solve();

    }

    private void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int n = scan.nextInt();
        int []a = new int[n];
        for (int i = 0; i < n; ++i){
            a[i] = scan.nextInt();
        }
        int m = scan.nextInt();

        HashSet hs = new HashSet();
        for(int i = 0; i < m; ++i)
            hs.add(scan.nextInt());

        ArrayList al = new ArrayList();
        for(int i = 0; i < n; ++i){
            if (hs.contains(a[i])){
                al.add(a[i]);
            }
        }

        writer.printf("%d\n", al.size());
        if (al.size()>0){
            for (int i = 0; i < al.size()-1; ++i){
                writer.printf("%d ", al.get(i));
            }
            writer.print(al.get(al.size()-1));
        }
        writer.close();
    }
}

