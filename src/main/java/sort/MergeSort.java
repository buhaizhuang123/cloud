package sort;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/3/4 14:54
 * @mark MergeSort 归并排序
 */
public class MergeSort {

    public void sort(int[] arr) {


        sort(arr, 0, arr.length - 1);

    }

    private void sort(int[] arr, int left, int right) {

        if (left >= right) return;
        int mid = (left + right) / 2;

        sort(arr, left, mid);
        sort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int s1 = left;
        int s2 = mid + 1;

        int init = 0;
        int[] temp = new int[right - left + 1];
        while (s1 <= mid && s2 <= right) {
            if (arr[s1] < arr[s2]) {
                temp[init++] = arr[s1++];
            } else {
                temp[init++] = arr[s2++];
            }
        }
        while (s1 <= mid) temp[init++] = arr[s1++];

        while (s2 <= right) temp[init++] = arr[s2++];

        for (int i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }

    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = AriUtils.randomArr(10);
        mergeSort.sort(arr);
        AriUtils.print(arr);
    }

}
