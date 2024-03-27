package singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author haizhuangbu
 * @date 2024/1/23 17:32
 * @mark LazySingletonPattern
 */
public class LazySingletonPattern {

    private static LazySingletonPattern lazySingletonPattern;

    private LazySingletonPattern() {
    }


    public static synchronized LazySingletonPattern getInstance() {
        if (lazySingletonPattern == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 锁对象
            synchronized (LazySingletonPattern.class) {
                // 双重校验
                if (lazySingletonPattern == null) {
                    lazySingletonPattern = new LazySingletonPattern();
                }
            }
            return lazySingletonPattern;
        }
        return lazySingletonPattern;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        for (int i = 0; i < 200; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    LazySingletonPattern instance = LazySingletonPattern.getInstance();
                    System.out.println(instance);
                }
            });
        }
        System.out.println("run over");
        executorService.shutdown();


    }

}
