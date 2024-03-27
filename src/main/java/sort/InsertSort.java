package sort;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/3/4 14:41
 * @mark InsertSort
 */
public class InsertSort {

    public void sort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {

            int j = i - 1;
            int temp = arr[i];
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        int[] arr = AriUtils.randomArr(10);
        insertSort.sort(arr);
        AriUtils.print(arr);
    }

}
