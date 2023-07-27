package util;

import com.sun.tools.hat.internal.parser.ReadBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2023/5/17 10:24
 * @mark AriUtils 算法工具类
 */
public class AriUtils {

    /**
     * 打印元素信息
     *
     * @param arr
     */
    public static void print(int[] arr) {

        String result = Arrays.stream(arr).mapToObj(i -> i + "").collect(Collectors.joining(",")).toString();
        System.out.println(result);


    }

    /**
     * 交换元素位置
     *
     * @param arr
     * @param sw1
     * @param sw2
     */
    public static void swap(int[] arr, int sw1, int sw2) {
        int temp = arr[sw1];
        arr[sw1] = arr[sw2];
        arr[sw2] = temp;
    }

    /**
     * 生成随机数
     *
     * @param size
     * @return
     */
    public static int[] randomArr(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }


    /**
     * 生成随机数
     *
     * @param size  daxiao
     * @param range fanwei
     * @return
     */
    public static int[] randomArr(int size, int range) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(range);
        }
        return arr;
    }

    public static void main(String[] args) {
        try {
            Process exec = Runtime.getRuntime().exec("ls -l");
            BufferedReader readBuffer = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            String line = "";
            while ((line = readBuffer.readLine()) != null) {
                System.out.println(line);
            }
            exec.waitFor();
            exec.destroy();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
