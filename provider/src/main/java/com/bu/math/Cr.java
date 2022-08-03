package com.bu.math;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/8/1 12:04
 * @mark Cr 插入排序
 */
public class Cr {

    @Test
    public void run() {
        int[] math = {1, 4, 51, 2, 13};
        match(math);
        System.out.println(Arrays.toString(math));
    }

    private int[] match(int[] math) {
        for (int i = 0; i < math.length; i++) {
            // 取出当前元素与左边元素对比. 保证当前元素为左边所有元素中最小的
            for (int j = i; j > 0 && math[j] < math[j - 1]; j--) {
                int now = math[j];
                math[j] = math[j - 1];
                math[j - 1] = now;
            }
        }
        return math;
    }

}
