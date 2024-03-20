package sort;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/3/4 15:28
 * @mark CounterSort 计数排序
 */
public class CounterSort {

    public void sort(int[] arr) {

        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
        }

        int[] counterCollection = new int[max + 1];

        for (int i = 0; i < arr.length; i++) {
            counterCollection[arr[i]] += 1;
        }

        for (int i1 = 1; i1 < counterCollection.length; i1++) {
            counterCollection[i1] += counterCollection[i1 - 1];
        }


        int[] temp = new int[arr.length];
        for (int i1 = 0; i1 < arr.length; i1++) {
            int idx = counterCollection[arr[i1]] - 1;
            temp[idx] = arr[i1];
            counterCollection[arr[i1]] -= 1;
        }

        for (int i1 = 0; i1 < temp.length; i1++) {
            arr[i1] = temp[i1];
        }


    }

    public static void main(String[] args) {
        CounterSort counterSort = new CounterSort();
        int[] arr = AriUtils.randomArr(10);
        counterSort.sort(arr);
        AriUtils.print(arr);
    }

}
