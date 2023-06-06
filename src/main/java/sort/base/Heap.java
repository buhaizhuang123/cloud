package sort.base;

import sort.Sort;
import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2023/6/6 09:28
 * @mark Heap 堆排序
 */
public class Heap extends Sort {
    @Override
    public void sort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapFly(arr, arr.length, i);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            AriUtils.swap(arr, i, 0);
            heapFly(arr, i, 0);
        }

    }

    private void heapFly(int[] arr, int length, int i) {

        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int root = i;
        if (left < length && arr[left] > arr[root]) root = left;
        if (right < length && arr[right] > arr[root]) root = right;
        if (root != i) {
            AriUtils.swap(arr, root, i);
            heapFly(arr, length, root);

        }


    }

    @Override
    public String getName() {
        return "Heap";
    }
}
