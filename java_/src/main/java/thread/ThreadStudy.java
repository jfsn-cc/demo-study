package thread;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadStudy{
    public static void main(String[] args) throws Exception {
        //CyclicBarrier barrier = new CyclicBarrier(2);
        /*for (int i = 0; i < 5; i++) {
            new Write(barrier, i).start();
        }
        barrier.await();*/
        //三个信号量
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 5; i++) {
            new Worke(semaphore, i).start();
        }
        AtomicInteger atomicInteger = new AtomicInteger(2);
        atomicInteger.compareAndSet(2,1);

/*        MyCallable myCallable = new MyCallable();
        Object call = myCallable.call();
        System.out.println(call);*/
       // new ThreadPoolExecutor();
    }

    static class Worke extends Thread{
        int num;
        Semaphore semaphore;
        public Worke(Semaphore semaphore, Integer num) {
            this.num = num;
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(2);
                System.out.println("这是第几个运行："+num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();

            }
        }
    }
    public void text0(){
        ExecutorService service = Executors.newFixedThreadPool(10);
        //ThreadPoolExecutor executor = new ThreadPoolExecutor();

        //RejectedExecutionHandler handler =
    /*            ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy() ;
                abortPolicy.rejectedExecution();*/
        List<Future> futures = new ArrayList<Future>();
        for (int i=0; i<10; i++) {
            Callable callable = new Callable() {
                @Override
                public Object call() throws Exception {
                    return null;
                }
            };
            Future future = service.submit(callable);
            futures.add(future);
        }
        service.shutdown();
        futures.stream().forEach(item->{
            System.out.println(item);
        });
    }
    public void ThreadPoolExecuor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        RejectedExecutionHandler defaultHandler;
  /*      ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,
                                            workQueue,Executors.defaultThreadFactory(),defaultHandler);*/
    }

    public static void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("线程一");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            System.out.println("线程2");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }).start();
        System.out.println("等待线程进行");
        countDownLatch.await();
        System.out.println("线程执行完毕");
    }

    public void testCyclicBarrier() {
        CyclicBarrier barrier = new CyclicBarrier(5);
    }
}

class Write extends Thread{
    int n ;
    CyclicBarrier barrier;
    public Write(CyclicBarrier barrier, int n){
        this.barrier = barrier;
        this.n = n;
    }
    @Override
    public void run() {
        try {
            TimeUnit.MICROSECONDS.sleep(30);
            System.out.println("执行当前任务: " + n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class MyCallable implements Callable {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
        System.out.println("test");
        return 1;
    }
}
