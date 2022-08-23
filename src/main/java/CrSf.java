import java.util.Arrays;
import java.util.HashMap;

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


        HashMap<Object, Object> map = new HashMap<>();
        System.out.println(map);

    }

    public static void sort(int[] arr) {

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

    }


}
