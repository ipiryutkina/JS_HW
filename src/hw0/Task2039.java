import java.io.PrintWriter;
import java.util.Scanner;

public class Task2039 {

    public static void main(String[] args) throws Exception {
        Task2039 task = new Task2039();
        task.solve();
    }
    //[aeiouy]{n,}
    private void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        String text = scan.nextLine();
        String [] lis = text.split("[^a-zA-Z]{1,}");
        writer.print(lis.length);


        /*
        int start = -1, i = 0, counter = 0;
        writer.printf("%d\n",text.length());
        while(i < text.length()){
            while(i < text.length() && text.charAt(i)>=32) {
                ++i;
            }
            writer.printf("%d\n",i);
            ++counter;
            while(i < text.length() && text.charAt(i)<32) {
                ++i;
            }

        }
*/

        writer.close();
    }
}