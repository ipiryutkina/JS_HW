package hw10;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.LongStream;

class FactTask implements Runnable {
    private final int n;

    public FactTask(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        long res = LongStream.rangeClosed(1, n)
                .reduce(1, (long x, long y) -> x * y);
        System.out.println(String.format("Thread %s: fact(%d) = %d", Thread.currentThread().getName(),
                n, res));
    }
}

public class MultiThreadFactorial {

    static final int MAX_T = 3;

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(new File("resources\\fact\\fact.txt"));
        List<Integer> integers = new ArrayList<>();
        while (scanner.hasNextInt()) {
            integers.add(scanner.nextInt());
        }

        int n = integers.size();

        /*
        for (int i = 0; i < n; ++i) {
            new Thread(new FactTask(integers.get(i))).start();
        }
        */

        ExecutorService pool = Executors.newCachedThreadPool();
        //ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        for (int i = 0; i < n; ++i) {
            pool.execute(new FactTask(integers.get(i)));
        }
        pool.shutdown();
    }

}
