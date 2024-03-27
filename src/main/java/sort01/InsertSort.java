package sort01;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/1/22 15:22
 * @mark InsertSort
 */
public class InsertSort {


    public void sort() {

        int[] arr = AriUtils.randomArr(10);
        AriUtils.print(arr);
        sort(arr);
        AriUtils.print(arr);
    }

    private void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > temp) {
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
