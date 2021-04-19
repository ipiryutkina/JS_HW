
import java.io.PrintWriter;
import java.util.Scanner;

public class Task2011 {
    /*
    //Need for testing only
    public static void main(String[] args) throws Exception {
        Task2011 task = new Task2011();
        task.solve();
    }
     */

    public void solve() throws Exception {

        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);

        int age = scan.nextInt();
        if (age < 7){
            writer.print("preschool child");
        }
        else if (age <= 17){
            writer.printf("schoolchild %d", age - 6);
        }
        else if (age <= 22){
            writer.printf("student %d", age - 17);
        }
        else{
            writer.print("specialist");
        }
        writer.close();
    }
}
