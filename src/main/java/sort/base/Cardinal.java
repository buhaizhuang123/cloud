package sort.base;

import sort.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/6/6 09:28
 * @mark Cardinal 基数排序
 */
public class Cardinal extends Sort {
    @Override
    public void sort(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
        }

        int count = (max + "").length();
        int j = 0;

        List<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        while (j < count) {
            // 入桶
            for (int i = 0; i < arr.length; i++) {
                int idx = (int) (arr[i] / Math.pow(10, j) % 10);
                buckets[idx].add(arr[i]);
            }
            int x = 0;
            // 出桶
            for (int i = 0; i < buckets.length; i++) {

                for (int z = 0; z < buckets[i].size(); z++) {
                    arr[x++] = buckets[i].get(z);
                }
                buckets[i].clear();
            }

            j++;
        }


    }

    @Override
    public String getName() {
        return "Cardinal";
    }
}
