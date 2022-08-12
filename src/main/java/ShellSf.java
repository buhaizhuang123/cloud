import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/8/12 10:39
 * @mark ShellSf shell排序算法 希尔算法
 */
public class ShellSf {


    public static void main(String[] args) {
        int[] arr = new int[]{9, 10, 3, 5, 1, 2};
        ShellSf shellSf = new ShellSf();
        shellSf.shell(arr);
        System.out.println(Arrays.toString(arr));
    }


    public void shell(int[] arr) {
        int now;
        for (int r = arr.length / 2; r >= 1; r /= 2) {
            for (int i = r; i < arr.length; i++) {
                int temp = arr[i];
                now = i - r;
                while (now >= 0 && temp < arr[now]) {
                    arr[now + r] = arr[now];
                    now -= r;
                }
                arr[now + r] = temp;
            }
        }
    }


}
