package sort;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/3/4 15:10
 * @mark FastSort 快速排序
 */
public class FastSort {

    public void sort(int[] arr) {

        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int left, int right) {

        if (left >= right) return;
        int base = findBase(arr, left, right);

        sort(arr, left, base - 1);
        sort(arr, base + 1, right);


    }

    private int findBase(int[] arr, int left, int right) {

        int p = right;

        int s1 = left;

        for (int s2 = left; s2 < right; s2++) {
            if (arr[s2] < arr[p]) AriUtils.swap(arr, s1++, s2);
        }
        AriUtils.swap(arr, s1, p);
        return s1;
    }

    public static void main(String[] args) {
        FastSort fastSort = new FastSort();

        int[] arr = AriUtils.randomArr(10);

        fastSort.sort(arr);
        AriUtils.print(arr);
    }

}
