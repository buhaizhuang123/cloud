package com.cloud.io;

import java.util.concurrent.*;

/**
 * @author haizhuangbu
 * @date 2022/7/21 13:24
 * @mark Di
 */
public class Di {

    private static Semaphore semaphore = new Semaphore(1);


    private static CountDownLatch count = new CountDownLatch(2);


    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        Runnable t1 = () -> {
            try {
                System.out.println("task1");
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("释放1");
                semaphore.release();
            }

        };

        executor.submit(t1);

        Runnable t2 = () -> {
            try {
                System.out.println("task2");
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("释放2");
                semaphore.release();
            }

        };
        executor.submit(t2);
        System.out.println("执行阻塞");
        try{
            semaphore.acquire(2);
            System.out.println("任务执行完毕");
        }finally {
            semaphore.release(2);
        }
        executor.shutdown();
    }

}
