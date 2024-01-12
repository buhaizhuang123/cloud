package sort.base;

import sort.Sort;

/**
 * @author haizhuangbu
 * @date 2023/6/6 09:27
 * @mark Count 计数排序
 */
public class Count extends Sort {
    @Override
    public void sort(int[] arr) {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
        }

        int[] counter = new int[max + 1];

        for (int i = 0; i < arr.length; i++) {
            counter[arr[i]] += 1;
        }

        for (int i = 1; i < counter.length; i++) {
            counter[i] += counter[i - 1];
        }
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int idx = counter[arr[i]] - 1;
            temp[idx] = arr[i];
            counter[arr[i]] -= 1;
        }


        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }

    }

    @Override
    public String getName() {
        return "Count";
    }
}
