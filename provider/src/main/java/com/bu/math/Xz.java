package com.bu.math;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/8/1 11:50
 * @mark Xz 选择排序算法 取出每个元素与后续元素对比。根据大小交换位置
 */
public class Xz {
    @Test
    public void run() {

        int[] math = {1, 4, 51, 2, 13};
        int[] match = match(math);
        System.out.println(Arrays.toString(match));

    }

    public int[] match(int[] math) {
        int length = math.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                // 若是后续节点数 小于 前节点 交换位置
                if (math[i] > math[j]) {
                    int now = math[i];
                    math[i] = math[j];
                    math[j] = now;
                }
            }
        }
        return math;
    }


}
