package sort;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/3/4 15:01
 * @mark ShellSort 希尔排序 减小增量排序
 */
public class ShellSort {

    public void sort(int[] arr) {

        for (int i = arr.length / 2; i > 0; i /= 2) {

            for (int j = i; j < arr.length; j++) {
                int temp = arr[j];
                int x = j - i;

                while (x >= 0 && arr[x] > temp) {
                    arr[x + i] = arr[x];
                    x -= i;
                }
                arr[x + i] = temp;

            }

        }

    }

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        int[] arr = AriUtils.randomArr(10);
        shellSort.sort(arr);
        AriUtils.print(arr);
    }

}
