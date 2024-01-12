package com.cloud.io;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author haizhuangbu
 * @date 2022/7/21 13:24
 * @mark Di
 */
public class Di {

    private static Semaphore semaphore = new Semaphore(2);


    private static CountDownLatch count = new CountDownLatch(2);


    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        for (int i = 0; i < 2; i++) {
            Runnable t1 = () -> {
                try {
                    System.out.println("task1");
                    semaphore.acquire(1);
                    System.out.println("pass1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("释放1");
                    semaphore.release(1);
                }

            };

            executor.submit(t1);

            Runnable t2 = () -> {
                try {
                    System.out.println("task2");
                    semaphore.acquire(1);
                    System.out.println("pass2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("释放2");
                    semaphore.release(1);
                }

            };
            executor.submit(t2);
        }

        executor.shutdown();
    }

    @Test
    public void tt() {

        String[] s = {"A", "B", "C"};


        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        AtomicInteger ite = new AtomicInteger(0);
        for (int i = 0; i < s.length; i++) {
            System.out.println(i);
            executor.execute(() -> {

                try {
                    System.out.println("s[i] = " + s[ite.get()]);
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    ite.incrementAndGet();
                    semaphore.release();
                }
            });
        }
        executor.shutdown();
    }


}
