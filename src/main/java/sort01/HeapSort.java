package sort01;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/1/22 15:36
 * @mark HeapSort
 */
public class HeapSort {


    public void sort() {

        int[] arr = AriUtils.randomArr(10);
        AriUtils.print(arr);
        sort(arr);
        AriUtils.print(arr);

    }

    private void sort(int[] arr) {

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapFly(arr, arr.length, i);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            AriUtils.swap(arr, i, 0);
            heapFly(arr, i, 0);
        }


    }

    private void heapFly(int[] arr, int length, int i) {
        int root = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        if (left < length && arr[left] > arr[root]) root = left;
        if (right < length && arr[right] > arr[root]) root = right;
        if (root != i) {
            AriUtils.swap(arr, root, i);
            heapFly(arr, length, root);
        }
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        heapSort.sort();
    }


}
