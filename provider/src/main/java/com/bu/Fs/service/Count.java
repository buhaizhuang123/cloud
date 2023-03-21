package com.bu.Fs.service;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2023/3/14 14:22
 * @mark Count
 */
public class Count {

    private  static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            System.out.println("运行A");
            countDownLatch.countDown();
            System.out.println(" run A is Over");
        });


        executorService.submit(() -> {
            System.out.println("运行B");
            countDownLatch.countDown();
            System.out.println(" run B is Over");
        });
        System.out.println("run all");
        countDownLatch.await();
        System.out.println("run over");
        executorService.shutdown();

        List<Person> list = new ArrayList<Person>();


        List<Person> boyList = list.stream().filter(i -> i.getSex().equals("男"))
                .collect(Collectors.toList());

        System.out.println("boyList = " + boyList);


    }


}
