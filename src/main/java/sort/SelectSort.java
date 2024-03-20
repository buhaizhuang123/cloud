package sort;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/3/4 14:34
 * @mark SelectSort
 */
public class SelectSort {

    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[min] > arr[j]) min = j;
            }
            AriUtils.swap(arr, i, min);
        }
    }


    public static void main(String[] args) {
        SelectSort selectSort = new SelectSort();
        int[] arr = AriUtils.randomArr(10);
        selectSort.sort(arr);
        AriUtils.print(arr);
    }

}
