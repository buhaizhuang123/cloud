package sort;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/3/4 14:28
 * @mark BubbleSort 冒泡排序
 */
public class BubbleSort {

    public void sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j + 1 < arr.length - i; j++) {

                if (arr[j] > arr[j + 1]) {

                    AriUtils.swap(arr, j, j + 1);

                }
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = AriUtils.randomArr(10);
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(arr);
        AriUtils.print(arr);
    }


}
