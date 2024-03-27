package sort;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/3/4 15:17
 * @mark HeapSort 堆排序
 */
public class HeapSort {

    public void sort(int[] arr) {

        for (int i1 = arr.length / 2 - 1; i1 >= 0; i1--) {
            heapFly(arr, i1, arr.length);
        }

        for (int i1 = arr.length - 1; i1 >= 0; i1--) {
            AriUtils.swap(arr, i1, 0);
            heapFly(arr, 0, i1);
        }

    }

    private void heapFly(int[] arr, int i, int length) {

        int root = i;

        int left = i * 2 + 1;
        int right = i * 2 + 2;

        if (left < length && arr[left] > arr[root]) root = left;
        if (right < length && arr[right] > arr[root]) root = right;

        if (root != i) {
            AriUtils.swap(arr, root, i);
            heapFly(arr, root, length);
        }

    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] arr = AriUtils.randomArr(10);
        heapSort.sort(arr);
        AriUtils.print(arr);
    }

}


