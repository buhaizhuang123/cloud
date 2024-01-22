package sort01;

import util.AriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2024/1/22 17:39
 * @mark BucketSort 桶排序
 */
public class BucketSort {


    public void sort() {

        int[] arr = AriUtils.randomArr(10);
        AriUtils.print(arr);
        sort(arr);

        AriUtils.print(arr);


    }

    private void sort(int[] arr) {

        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
            if (min > arr[i]) min = arr[i];
        }

        int size = (max - min) / arr.length + 1;
        int cnt = (max - min) / size + 1;

        List<Integer>[] buckets = new ArrayList[cnt];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        // 入桶
        for (int i = 0; i < arr.length; i++) {
            int n = (arr[i] - min) / size;
            buckets[n].add(arr[i]);
        }

        // 桶内元素排序
        for (int i = 0; i < buckets.length; i++) {
            buckets[i].sort(null);
        }

        int n = 0;
        // 桶内元素取出
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[n++] = buckets[i].get(j);
            }
        }


    }


    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        bucketSort.sort();
    }

}
