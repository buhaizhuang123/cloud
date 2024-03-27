package sort01;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/1/22 20:29
 * @mark CounterSort 计数排序
 */
public class CounterSort {


    public void sort() {

        int[] arr = AriUtils.randomArr(10);
        AriUtils.print(arr);
        sort(arr);
        AriUtils.print(arr);
    }

    private void sort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        int[] counter = new int[max + 1];

        // 计数 每个值 出现的次数
        for (int i = 0; i < arr.length; i++) {
            counter[arr[i]] += 1;
        }

        // 记录值所在位置
        for (int i = 1; i < counter.length; i++) {
            counter[i] += counter[i - 1];
        }
        // 当前counter为有序序列、将有序序列中需要的值,取出放入 原始数组的copy对象中
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int idx = counter[arr[i]] - 1;
            temp[idx] = arr[i];
            counter[arr[i]] -= 1;
        }
        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }


    }


    public static void main(String[] args) {
        CounterSort counterSort = new CounterSort();
        counterSort.sort();
    }

}
