package sort.base;

import sort.Sort;

/**
 * @author haizhuangbu
 * @date 2023/6/6 09:16
 * @mark Insert 插入排序
 */
public class Insert extends Sort {
    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j--];
            }
            arr[j + 1] = temp;

        }
    }

    @Override
    public String getName() {
        return "Insert";
    }
}
