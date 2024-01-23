package sort01;

import util.AriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2024/1/23 10:40
 * @mark CardinalSort
 */
public class CardinalSort {

    public void sort() {

        int[] arr = AriUtils.randomArr(10);
        AriUtils.print(arr);

        sort(arr);

        AriUtils.print(arr);

    }

    private void sort(int[] arr) {

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
        }

        int len = (max + "").length();

        List<Integer>[] buckets = new ArrayList[10];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        int j = 0;

        while (j <= len) {

            // 入桶
            for (int i = 0; i < arr.length; i++) {
                int idx = (int) (arr[i] / Math.pow(10, j) % 10);
                buckets[idx].add(arr[i]);
            }
            int temp = 0;
            for (int i1 = 0; i1 < buckets.length; i1++) {
                for (int i = 0; i < buckets[i1].size(); i++) {
                    arr[temp++] = buckets[i1].get(i);
                }
                buckets[i1].clear();
            }
            j++;
        }


    }


    public static void main(String[] args) {
        CardinalSort cardinalSort = new CardinalSort();
        cardinalSort.sort();
    }


}
