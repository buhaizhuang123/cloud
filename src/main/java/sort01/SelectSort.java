package sort01;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/1/22 15:26
 * @mark SelectSort
 */
public class SelectSort {

    public void sort() {
        int[] arr = AriUtils.randomArr(10);
        AriUtils.print(arr);
        for (int i = 0; i < arr.length; i++) {
            // 每次循环的最小元素位置
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            // 将最小元素插入到第N次循环的位置
            AriUtils.swap(arr, min, i);
        }
        AriUtils.print(arr);
    }


    public static void main(String[] args) {
        SelectSort selectSort = new SelectSort();
        selectSort.sort();
    }

}
