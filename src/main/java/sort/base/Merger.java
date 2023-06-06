package sort.base;

import sort.Sort;

/**
 * @author haizhuangbu
 * @date 2023/6/6 09:19
 * @mark Merger 归并排序
 */
public class Merger extends Sort {
    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        merger(arr, left, mid, right);
    }

    private void merger(int[] arr, int left, int mid, int right) {

        int s1 = left;
        int s2 = mid + 1;
        int[] temp = new int[right - left + 1];
        int i = 0;
        while (s1 <= mid && s2 <= right) {
            if (arr[s1] < arr[s2]) temp[i++] = arr[s1++];
            else temp[i++] = arr[s2++];
        }

        while (s1 <= mid) {
            temp[i++] = arr[s1++];
        }

        while (s2 <= right) {
            temp[i++] = arr[s2++];
        }

        for (int j = 0; j < temp.length; j++) {
            arr[left + j] = temp[j];
        }


    }

    @Override
    public String getName() {
        return "Merger";
    }
}
