package sort;

import util.AriUtils;

import java.util.ArrayList;

/**
 * @author haizhuangbu
 * @date 2024/3/4 15:35
 * @mark BucketSort
 */
public class BucketSort {


    public void sort(int[] arr) {

        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];

            if (min > arr[i]) min = arr[i];
        }

        int size = (max - min) / arr.length + 1;
        int cnt = (max - min) / size + 1;

        ArrayList<Integer>[] arrayLists = new ArrayList[cnt];
        for (int i1 = 0; i1 < arrayLists.length; i1++) {
            arrayLists[i1] = new ArrayList();
        }
        // 入桶
        for (int i = 0; i < arr.length; i++) {
            int idx = arr[i] / size;
            arrayLists[idx].add(arr[i]);
        }
        // 桶内元素排序
        for (int i = 0; i < arrayLists.length; i++) {
            arrayLists[i].sort(null);
        }

        int init = 0;
        // 将桶内元素取出
        for (int i = 0; i < arrayLists.length; i++) {
            for (int j = 0; j < arrayLists[i].size(); j++) {
                arr[init++] = arrayLists[i].get(j);
            }
        }


    }

    public static void main(String[] args) {
        BucketSort bucketSort = new BucketSort();
        int[] arr = AriUtils.randomArr(10);
        bucketSort.sort(arr);
        AriUtils.print(arr);
    }

}
