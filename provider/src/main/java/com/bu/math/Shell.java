package com.bu.math;

/**
 * @author haizhuangbu
 * @date 2022/8/3 11:00
 * @mark Shell shell排序算法
 */
public class Shell {


    public void run() {

        int[] math = {1, 5, 2, 7};
        match(math);


    }

    private void match(int[] math) {
        int temp, z;
        for (int i = math.length / 2; i >= 1; i /= 2) {
            for (int j = i; j < math.length; j++) {
                temp = math[j];
                z = j - i;

            }
        }


    }

}
