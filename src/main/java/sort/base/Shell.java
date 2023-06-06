package sort.base;

import sort.Sort;

/**
 * @author haizhuangbu
 * @date 2023/6/6 09:28
 * @mark Shell 希尔排序
 */
public class Shell extends Sort {
    @Override
    public void sort(int[] arr) {
        for (int i = arr.length / 2; i > 0; i /= 2) {
            for (int j = i; j < arr.length; j++) {
                int n = j - i;
                int temp = arr[j];
                while (n >= 0 && arr[n] > temp) {
                    arr[n + i] = arr[n];
                    n -= i;
                }
                arr[n + i] = temp;
            }

        }
    }

    @Override
    public String getName() {
        return "Shell";
    }
}
