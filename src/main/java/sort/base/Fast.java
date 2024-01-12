package sort.base;

import sort.Sort;
import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2023/6/6 09:29
 * @mark Fast 快速排序
 */
public class Fast extends Sort {
    @Override
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
        int provid = right;

        int s1 = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < arr[provid]) AriUtils.swap(arr, s1++, i);
        }
        AriUtils.swap(arr, provid, s1);
        return s1;
    }

    @Override
    public String getName() {
        return "Fast";
    }
}
