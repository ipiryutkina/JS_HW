package hw11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {

    private BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private final List<Thread> threads = new ArrayList<>();


    public FixedThreadPool(int nThreads) {
        for (int i = 0; i < nThreads; ++i) {
            threads.add(new Thread(
                            () -> {
                                while (true) {
                                    try {
                                        Runnable r = taskQueue.take();
                                        if (r != null)
                                            r.run();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                    )
            );
        }
    }

    @Override
    public void start() {
        for (Thread t : threads) {
            t.start();
        }
    }

    @Override
    public void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*public void waitAll() throws InterruptedException {
        for (Thread t : threads) t.join();
    }*/

    public static void main(String[] args) throws InterruptedException {
        FixedThreadPool pool = new FixedThreadPool(10);
        pool.start();
        pool.execute(() -> System.out.println("First print task"));
        pool.execute(() -> System.out.println("Second print task"));

        //pool.waitAll();
    }
}
