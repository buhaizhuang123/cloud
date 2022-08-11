import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/8/11 11:44
 * @mark GbSf 归并排序算法 分治迭代算法
 */
public class GbSf {

    public static void main(String[] args) {

        int[] arr = {9, 8, 7, 6, 55, 33};
        System.out.println(Arrays.toString(arr));
        GbSf gbSf = new GbSf();
        gbSf.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 范围
     *
     * @param low
     * @param height
     */
    public void sort(int[] arr, int low, int height) {
        if (low >= height) return;
        // 取二分查找中间量
        int mid = (low + height) / 2;
        sort(arr, low, mid);
        sort(arr, mid + 1, height);
        merge(arr, low, mid, height);
        System.out.println(Arrays.toString(arr));
    }

    public void merge(int[] arr, int low, int mid, int height) {
        // 临时
        int[] temp = new int[height - low + 1];
        int i = 0;
        int s1 = low;
        int s2 = mid + 1;
        while (s1 <= mid && s2 <= height) {
            if (arr[s1] <= arr[s2]) {
                temp[i++] = arr[s1++];
            } else {
                temp[i++] = arr[s2++];
            }
        }
        // 若是存在左多 右少
        while (s1 <= mid) {
            temp[i++] = arr[s1++];
        }
        // 若是存在左少 右多
        while (s2 <= height) {
            temp[i++] = arr[s2++];
        }

        for (int j = 0; j < temp.length; j++) {
            arr[j + low] = temp[j];
        }

    }


}
