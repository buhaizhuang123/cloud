package sort01;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/1/22 16:46
 * @mark FastSort
 */
public class FastSort {


    public void sort() {

        int[] arr = AriUtils.randomArr(10);
        AriUtils.print(arr);
        sort(arr, 0, arr.length - 1);
        AriUtils.print(arr);
    }

    private void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int base = findBase(arr, left, right);
        sort(arr, left, base - 1);
        sort(arr, base + 1, right);

    }

    private int findBase(int[] arr, int left, int right) {
        int s1 = left;
        int p = right;
        // 18 2 17 4 5
        for (int s2 = left; s2 < right; s2++) {
            if (arr[s2] < arr[p]) {
                // 2 18 17 4 5
                // 2 18 17 4 5
                // 2 4 17 18 5
                AriUtils.swap(arr, s1++, s2);
            }
        }
        // 2 4 5 18 17
        AriUtils.swap(arr, s1, p);
        return s1;
    }

    public static void main(String[] args) {
        FastSort fastSort = new FastSort();
        fastSort.sort();
    }

}
