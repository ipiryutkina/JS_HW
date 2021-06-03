package hw11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ScalableThreadPool implements ThreadPool {

    private BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private final HashSet<Thread> threads = new HashSet<>();
    int minThread;
    int maxThread;

    public ScalableThreadPool(int nMin, int nMax) {
        minThread=nMin;
        maxThread = nMax;

        for (int i = 0; i < nMin; ++i) {
            newThread();
        }
    }

    int t;

    private void newThread() {
        threads.add(new Thread(
                        () -> {
                            while (true) {




                                try {
                                    Runnable r = taskQueue.poll(100, TimeUnit.MILLISECONDS);
                                    if (r != null) // X
                                    {
                                        r.run();  // Y
                                    }
                                    else
                                    {
                                        synchronized (threads)
                                        {
                                            if(threads.size()> minThread)
                                                threads.remove(Thread.currentThread());
                                        }
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                )
        );
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
            synchronized (threads)
            {
                if(!taskQueue.isEmpty() && threads.size()<maxThread)
                {
                    newThread();
                }
            }

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


    }
}

