package tx;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/8/12 08:34
 * @mark Ts
 */
public class Ts {


    public static void main(String[] args) {
        int[] arr = new int[]{5, 1, 3, 2, 56, 3};
// 冒泡排序
//        for (int i = 1; i <= arr.length; i++) {
//            for (int j = 0; j < arr.length - i; j++) {
//                // 比较相邻两个元素的大小
//                if (arr[j + 1] < arr[j]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
        // 插入
//        for (int i = 0; i < arr.length; i++) {
//            int now = arr[i]; // 当前值
//            int j = i - 1; // 前值位置 第一次循环为-1
//            while (j >= 0 && now < arr[j]) { // 比较当前值与前值 若是当前值小于前值 循环比较
//                arr[j + 1] = arr[j]; // 将当前值向后位移
//                j--; // 满足条件继续循环
//            }
//            // 推出循环 代表当前值处于合适的位置。将当前值插入当前值位置
//            arr[j + 1] = now;
//        }
//      选择排序
//        for (int i = 0; i < arr.length - 1; i++) {
//            int j = i + 1;
//            while (j <= arr.length - 1) { // 循环当前队列所有值 与 当前值比较
//                if (arr[i] > arr[j]) { //  若是当前值与后续值比较小于 交换位置
//                    // 交换位置
//                    int temp = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = temp;
//                }
//                j++;
//            }
//
//        }

        Ts ts = new Ts();
        // 归并排序
//        ts.hb(arr, 0, arr.length - 1);
        // shell排序
        ts.shell(arr);
        System.out.println(Arrays.toString(arr));
    }


    public void hb(int[] arr, int low, int height) {

        if (low >= height) return;
        int mid = (low + height) / 2;
        hb(arr, low, mid); // 左边
        hb(arr, mid + 1, height); // 右边
        megry(arr, low, mid, height); // 比较大小
    }

    private void megry(int[] arr, int low, int mid, int height) {
        int s1 = low; // 左边值
        int s2 = mid + 1; // 右边值
        int[] temp = new int[height - low + 1]; // 容器存放比较结果
        int i = 0;
        // 左边下标不能大于mid 右边不能大于height
        while (s1 <= mid && s2 <= height) {
            if (arr[s1] >= arr[s2]) {
                temp[i++] = arr[s2++];
            } else {
                temp[i++] = arr[s1++];
            }
        }

        while (s1 <= mid) {
            temp[i++] = arr[s1++];
        }

        while (s2 <= height) {
            temp[i++] = arr[s2++];
        }

        for (int j = 0; j < temp.length; j++) {
            arr[low + j] = temp[j];
        }
    }


    public void shell(int[] arr) {
        int z;
        // 1 2 3 4 5 6
        for (int i = arr.length / 2; i >= 1; i /= 2) {
            for (int j = i; j < arr.length; j++) {

                // 当前值
                int now = arr[j];
                z = j - i;
                // 前值
                while (z >= 0 && now < arr[z]) {
                    arr[z + i] = arr[z];
                    z -= i;
                }
                arr[i + z] = now;
            }


        }


    }


}
