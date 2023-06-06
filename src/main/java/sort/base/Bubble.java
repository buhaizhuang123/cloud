package sort.base;

import sort.Sort;
import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2023/6/6 09:02
 * @mark Bubble 冒泡排序
 */
public class Bubble extends Sort {


    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j + 1 < arr.length - i; j++) {

                if (arr[j] > arr[j + 1]) AriUtils.swap(arr, j, j + 1);

            }

        }
    }

    @Override
    public String getName() {
        return "Bubble";
    }
}
