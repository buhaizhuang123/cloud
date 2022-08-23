package shell;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/8/12 17:18
 * @mark shell shell 排序
 */
public class Shell {


    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 2, 4, 1};
        shell(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void shell(int[] arr) {
        for (int inter = arr.length / 2; inter >= 1; inter /= 2) {
            for (int i = inter; i < arr.length; i++) {
                int n =i - inter;
                int temp = arr[i];
                while (n >= 0 && temp < arr[n]) {
                    arr[n + inter] = arr[n];
                    n -= inter;
                }
                arr[n + inter] = temp;
            }
        }
    }


}
