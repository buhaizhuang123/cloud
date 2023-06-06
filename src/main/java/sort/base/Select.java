package sort.base;

import sort.Sort;
import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2023/6/6 09:07
 * @mark Select 选择排序
 */
public class Select extends Sort {
    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = i;
            int j = i + 1;
            for (; j < arr.length; j++) {
                if (arr[j] < arr[temp]) {
                    temp = j;
                }
            }
            AriUtils.swap(arr, temp, i);
        }
    }

    @Override
    public String getName() {
        return "Select";
    }
}
