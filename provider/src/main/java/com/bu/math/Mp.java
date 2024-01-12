package com.bu.math;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author haizhuangbu
 * @date 2022/8/4 15:07
 * @mark Mp 冒泡排序算法  最大最小值排到最后位置
 */
public class Mp {

    @Test
    public void run() {
        int[] math = {9, 1, 10, 5};
        match(math);
    }

    private void match(int[] math) {


        Condition condition = new ReentrantLock().newCondition();
        if (Thread.interrupted()) {
            try {
                ServiceLoader<?> load = ServiceLoader.load(Class.forName(""));
                Iterator<?> iterator = load.iterator();
                while (iterator.hasNext()) {
                    Object next = iterator.next();

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < math.length; i++) {
            // 每次循环保证最大的值 在队列最后面
            for (int j = 0; j < math.length - 1 - i; j++) {
                if (math[j] > math[j+1]) {
                    int t = math[j+1];
                    math[j+1] = math[j];
                    math[j] = t;
                }
            }
        }
        System.out.println(Arrays.toString(math));

    }


}
