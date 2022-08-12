import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/8/12 13:19
 * @mark Shell shell
 */
public class Shell {

    public static void main(String[] args) {
        int[] arr = new int[]{8, 9, 1, 7, 1, 6};
        Shell shell = new Shell();
//        shell.shell(arr);
//        System.out.println("shell" + Arrays.toString(arr));

//        shell.maoPao(arr);
//        System.out.println("maoPao" + Arrays.toString(arr));
//

//        shell.xuanZe(arr);
//        System.out.println("xuanZe = " + Arrays.toString(arr));

//        shell.chaRu(arr);
//        System.out.println("chaRu = " + Arrays.toString(arr));


        shell.heBin(arr, 0, arr.length - 1);
        System.out.println("heBin = " + Arrays.toString(arr));

    }

    /**
     * shell 算法
     */
    public void shell(int[] arr) {
        // interval 间隔 1 2 3 4
        for (int interval = arr.length / 2; interval >= 1; interval /= 2) {
            for (int i = interval; i < arr.length; i++) {
                int j = i - interval;  // 前值位置
                int now = arr[i];
                // 使用插入方式 修改值 插入算法用到
                while (j >= 0 && now < arr[j]) {
                    arr[j + interval] = arr[j];
                    j -= interval;
                }
                arr[j + interval] = now;
            }
        }


    }


    /**
     * maoPao 冒泡算法
     */
    public void maoPao(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int now = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = now;
                }
            }
        }

    }

    /**
     * chaRu 插入排序 8, 9, 1, 7, 1, 6
     */
    public void chaRu(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int now = arr[i];
            System.out.println("j = " + j);
            while (j >= 0 && now < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            System.out.println("now = " + now);
            arr[j + 1] = now;
        }
    }


    /**
     * xuanZe 选择排序
     */
    public void xuanZe(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int now = arr[i];
                    arr[i] = arr[j];
                    arr[j] = now;
                }
            }
        }
    }


    /**
     * heBin 合并排序
     */
    public void heBin(int[] arr, int low, int height) {

        if (low >= height) return;
        int mid = (low + height) / 2;

        heBin(arr, low, mid);
        heBin(arr, mid + 1, height);
        mergy(arr, low, mid, height);

    }

    private void mergy(int[] arr, int low, int mid, int height) {
        int s1 = low;
        int s2 = mid + 1;

        int[] temp = new int[height - low + 1];
        int idnex = 0;
        while (s1 <= mid && s2 <= height) {

            if (arr[s1] > arr[s2]) {
                temp[idnex++] = arr[s2++];
            } else {
                temp[idnex++] = arr[s1++];
            }
        }

        while (s1 <= mid) {
            temp[idnex++] = arr[s1++];
        }

        while (s2 <= height) {
            temp[idnex++] = arr[s2++];
        }

        for (int i = 0; i < temp.length; i++) {

            arr[low + i] = temp[i];


        }

    }


}
