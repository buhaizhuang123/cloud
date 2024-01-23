package sort02;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/1/23 14:25
 * @mark InsertSort
 */
public class InsertSort {

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
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int temp = arr[i];
            while (j >= 0 && temp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        insertSort.sort();
    }

}
