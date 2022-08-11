import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/8/11 18:38
 * @mark XzSf 选择排序算法
 */
public class XzSf {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 3, 5, 5, 2};
        xz(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void xz(int[] arr) {
        // 每个元素与后续所有元素对比 将最小的值放到最左边
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


}
