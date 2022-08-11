import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/8/11 17:29
 * @mark MpSf 冒泡排序算法
 */
public class MpSf {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 9, 2, 21, 4, 2, 1};
        mp(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void mp(int[] arr) {


        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

    }


}
