package sort02;

import sort01.BucketSort;
import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/1/23 13:45
 * @mark BubbleSort 冒泡排序
 */
public class BubbleSort {


    public void sort() {

        int[] arr = new int[5];
        arr[0] = 4;
        arr[1] = 8;
        arr[2] = 6;
        arr[3] = 5;
        arr[4] = 7;
        AriUtils.print(arr);
        sort(arr);
        AriUtils.print(arr);

    }

    private void sort(int[] arr) {
        // 外循环一次,确认一个极值
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j + 1 < arr.length; j++) {
                // 将大元素向后移动
                if (arr[j] > arr[j + 1]) {
                    AriUtils.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort();
    }

}
