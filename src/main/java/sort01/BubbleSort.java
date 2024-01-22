package sort01;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/1/22 15:18
 * @mark BubbleSort
 */
public class BubbleSort {


    public void sort() {

        int[] arr = AriUtils.randomArr(10);
        AriUtils.print(arr);
        sort(arr);
        AriUtils.print(arr);


    }

    private void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j + 1 < arr.length - i; j++) {
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
