package sort.base;

import sort.Sort;
import util.AriUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2023/6/6 09:02
 * @mark Bubble 冒泡排序
 */
public class Bubble extends Sort {


    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {

            for (int j = 0; j + 1 < arr.length - i; j++) {

                if (arr[j] > arr[j + 1]) AriUtils.swap(arr, j, j + 1);

            }

        }
    }

    @Override
    public String getName() {
        return "Bubble";
    }


    public static void main(String[] args) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        try {
            Date parse = simpleDateFormat.parse("20230614205006");
            System.out.println(parse);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
}
