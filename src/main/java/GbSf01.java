import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/8/11 15:49
 * @mark GbSf01
 */
public class GbSf01 {


    public static void main(String[] args) {
        int[] array = new int[]{1, 8, 9, 5, 3, 8};

        GbSf01 gbSf01 = new GbSf01();
        gbSf01.sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * @param arr
     * @param height
     * @param low
     */
    public void sort(int[] arr, int low, int height) {

        if (low >= height) return;
        int mid = (low + height) / 2;
        // left
        sort(arr, low, mid);
        // right
        sort(arr, mid + 1, height);
        merge(arr, low, mid, height);
        System.out.println(Arrays.toString(arr));
    }

    private void merge(int[] arr, int low, int mid, int height) {
        System.out.println("arr = " + Arrays.toString(arr));
        int s1 = low;
        int s2 = mid + 1;
        int i = 0;
        int[] temp = new int[height - low + 1];
        while (s1 <= mid && s2 <= height) {
            if (arr[s1] <= arr[s2]) {
                temp[i++] = arr[s1++];
            } else {
                temp[i++] = arr[s2++];
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


}
