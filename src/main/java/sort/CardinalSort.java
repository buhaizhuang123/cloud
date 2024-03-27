package sort;

import util.AriUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author haizhuangbu
 * @date 2024/3/4 15:48
 * @mark CardinalSort 基数排序
 */
public class CardinalSort {

    public void sort(int[] arr) {

        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (max < arr[i]) max = arr[i];
        }

        int cnt = (max + "").length();

        ArrayList<Integer>[] arrayLists = new ArrayList[10];
        for (int i = 0; i < arrayLists.length; i++) {
            arrayLists[i] = new ArrayList<>();
        }

        int j = 0;

        while (j <= cnt) {

            for (int i = 0; i < arr.length; i++) {
                int idx = (int) (arr[i] / Math.pow(10, j) % 10);
                arrayLists[idx].add(arr[i]);
            }

            int init = 0;

            for (int i = 0; i < arrayLists.length; i++) {

                for (int z = 0; z < arrayLists[i].size(); z++) {
                    arr[init++] = arrayLists[i].get(z);
                }
                arrayLists[i].clear();
            }

            j++;

        }

    }

    public static void main(String[] args) throws InterruptedException {
        CardinalSort cardinalSort = new CardinalSort();
        int[] arr = AriUtils.randomArr(10);
        cardinalSort.sort(arr);
        AriUtils.print(arr);
    }


}
