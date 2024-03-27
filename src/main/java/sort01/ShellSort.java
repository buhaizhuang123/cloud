package sort01;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/1/22 17:34
 * @mark ShellSort
 */
public class ShellSort {


    public void sort() {

        int[] arr = AriUtils.randomArr(10);
        AriUtils.print(arr);
        for (int i = arr.length / 2; i > 0; i /= 2) {
            for (int j = i; j < arr.length; j++) {
                int temp = arr[j];
                int n = j - i;
                while (n >= 0 && temp < arr[n]) {
                    arr[n + i] = arr[n];
                    n -= i;
                }
                arr[n + i] = temp;
            }
        }
        AriUtils.print(arr);
    }


    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        shellSort.sort();
    }
}
