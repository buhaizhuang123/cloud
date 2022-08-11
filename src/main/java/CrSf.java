import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/8/11 16:32
 * @mark CrSf 插入算法
 */
public class CrSf {


    public static void main(String[] args) {
        int[] array = new int[]{1, 8, 9, 5, 3, 8};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] arr) {


        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // 当前元素 与 后面所有元素做比较
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

    }


}
