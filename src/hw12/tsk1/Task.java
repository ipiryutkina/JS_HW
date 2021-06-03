package hw12.tsk1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


public class Task<T> {


    private static class CallableImpl implements Callable<String> {
        public CallableImpl() {

        }

        @Override
        public String call() throws Exception {

            return Thread.currentThread().getName();
        }
    }

    private static class RunnableImpl<T> implements Runnable {

        private final Task<T> task;

        public RunnableImpl(Task<T> task) {
            this.task = task;
        }

        @Override
        public void run() {
            try {
                task.get();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private final Callable<? extends T> callable;
    //private volatile boolean ready = false;
    private volatile Exception ex = null;
    volatile T res;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() throws Exception {

        /*if (ready) {
            System.out.println(Thread.currentThread().getName() + ": Value is ready.");
            return res;
        } else if (ex != null) {
            throw new RuntimeException(ex);
        }*/
        Thread.currentThread().sleep(1000 * new Random().nextInt(5));

        if (ex != null) {
            throw new RuntimeException(ex);
        }

        if (res != null) {
            System.out.println(Thread.currentThread().getName() + ": Value is ready.");
            return res;
        }

        synchronized (callable) {
            try {
                res = callable.call();//Future<Integer> future = executorService.submit(task);
                //ready = true;
                System.out.println(Thread.currentThread().getName() + ": Found value.");
                //System.out.println(ready);
                return res;
            } catch (Exception e) {
                ex = e;
                throw new RuntimeException(e);
            }

        }

    }


    public static void main(String[] args) throws Exception {

        /*
        ExecutorService pool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 100; ++i) {
            pool.execute(new RunnableImpl(new Task(new CallableImpl())));
        }
        pool.shutdown();
        */
        /*
        int nThreads = 3;
        List<Thread> threads = new ArrayList<>(3);
        for (int i = 0; i < nThreads; ++i) {
            new Thread(new RunnableImpl(new Task(new CallableImpl()))).start();
          */

        Runnable r1 = new RunnableImpl(new Task(new CallableImpl()));
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        Thread t3 = new Thread(r1);
        t1.start();
        t2.start();
        t3.start();
        System.out.println("______________");
    }
}
