package hw12.tsk2;

import jdk.nashorn.internal.runtime.Debug;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class ContextImpl implements Context {
    private LinkedList<Runnable> taskQueue;

    private  static final int maxNThreads  = 300;

    private volatile AtomicInteger completedCount =new AtomicInteger(0);
    private volatile AtomicInteger failedCount=new AtomicInteger(0);
    private volatile AtomicInteger interruptedCount=new AtomicInteger(0);
    private final int total;

    private Runnable mCallback;
    private volatile boolean mCallbackComplete=false;

    public ContextImpl(Runnable callback, List<Runnable> tasks) {
        total=tasks.size();
        int nThreads = Math.min(maxNThreads, tasks.size());
        taskQueue = new LinkedList<Runnable>(tasks);
        mCallback=callback;

        for (int i = 0; i < nThreads; ++i) {
            new Thread(() -> {
                 while(true)
                 {
                    try {
                        Runnable r;
                        synchronized (taskQueue)
                        {
                            if(taskQueue.isEmpty())
                                break;
                            else
                                r = taskQueue.remove();
                        }

                        if(r!=null)
                            r.run();

                        completedCount.incrementAndGet();
                    }
                    catch (Exception e) {
                        failedCount.incrementAndGet();
                    }
                }

                 if(isFinished())
                 {
                     synchronized (mCallback)
                     {
                         if(!mCallbackComplete)
                         {
                             mCallback.run();
                             mCallbackComplete=true;
                         }
                     }
                 }

            }
            ).start();
        }

    }

    @Override
    public int getCompletedTaskCount() {
        return completedCount.get();
    }

    @Override
    public int getFailedTaskCount() {
        return failedCount.get();
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedCount.get();
    }

    @Override
    public void interrupt() {

        synchronized (taskQueue)
        {
            interruptedCount.set(taskQueue.size());
            taskQueue.clear();
        }
    }

    @Override
    public boolean isFinished() {
        return completedCount.get() + interruptedCount.get() + failedCount.get() == total;
    }



}


class ExecutionManagerImpl implements ExecutionManager {
    @Override
    public Context execute(Runnable callback, List<Runnable> tasks) {
        return new ContextImpl(callback, tasks);
    }
}


public class ExecutionManagerTest {

    public static class Task implements Runnable {

        @Override
        public void run() {
            try {
                Thread.currentThread().sleep(3 * new Random().nextInt(5));
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws Exception {
        ExecutionManager em = new ExecutionManagerImpl();

        List<Runnable> lis = new ArrayList<>();
        for (int i = 0; i < 1000000; ++i) {
            lis.add(new Task());
        }
        Context ctx = em.execute(()->{ System.out.println("Complete callback");},lis);



    }
}
