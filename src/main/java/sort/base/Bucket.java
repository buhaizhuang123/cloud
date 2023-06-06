package sort.base;

import sort.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/6/6 09:27
 * @mark Bucket 桶排序
 */
public class Bucket extends Sort {
    @Override
    public void sort(int[] arr) {
        int max = arr[0];
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
            if (min > arr[i]) min = arr[i];
        }

        int size = (max - min) / arr.length + 1;
        int count = (max - min) / size + 1;

        List<Integer>[] buckets = new ArrayList[count];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // 入桶
        for (int i = 0; i < arr.length; i++) {
            int idx = (arr[i] - min) / size;
            buckets[idx].add(arr[i]);
        }
        // 桶内元素排序
        for (int i = 0; i < buckets.length; i++) {
            buckets[i].sort(null);
        }
        int x = 0;
        // 将桶内元素取出
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[x++] = buckets[i].get(j);
            }
        }
    }

    @Override
    public String getName() {
        return "Bucket";
    }
}
