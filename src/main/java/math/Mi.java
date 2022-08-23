package math;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/8/17 13:08
 * @mark Mi
 */
public class Mi {

    /**
     *
     */
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 4, 2, 6};
//        选择排序算法
//        for (int i = 0; i < arr.length; i++) {
//            int temp = arr[i];
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[j] < temp) {
//                    temp = arr[j];
//                }
//            }
//            arr[i] = temp;
//        }
//      插入排序

        ArrayList<Object> objects = new ArrayList<>();

        for (int i = 1; i < arr.length; i++) {
            int t = arr[i]; // 当前
            int j = i - 1;
            // 前数字大于当前数字。前数字值改变。
            while (j >= 0 && t < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            // 排在j+1当期的位置
            arr[j + 1] = t;
        }
        System.out.println(Arrays.toString(arr));
    }

}
