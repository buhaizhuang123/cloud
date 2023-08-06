package sort;

import util.AriUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/6/28 15:26
 * @mark Sort02
 */
public abstract class Sort02 {

    abstract void sort(int[] arr);

    // 冒泡
    public static class Bubble extends Sort02 {

        @Override
        void sort(int[] arr) {

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j + 1 < arr.length - i; j++) {
                    if (arr[j] > arr[j + 1]) AriUtils.swap(arr, j, j + 1);
                }
            }
        }
    }

    // 插入
    public static class Insert extends Sort02 {

        @Override
        void sort(int[] arr) {

            for (int i = 1; i < arr.length; i++) {
                int j = i - 1;
                int temp = arr[i];

                while (j >= 0 && arr[j] > temp) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = temp;
            }
        }
    }

    // 选择
    public static class Select extends Sort02 {

        @Override
        void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {

                int min = i;
                int j = i + 1;
                while (j < arr.length) {
                    if (arr[j] < arr[min]) {
                        min = j;
                    }
                    j++;
                }
                AriUtils.swap(arr, min, i);

            }
        }
    }

    // 快速
    public static class Fast extends Sort02 {

        @Override
        void sort(int[] arr) {


            sort(arr, 0, arr.length - 1);

        }

        private void sort(int[] arr, int left, int right) {
            if (left >= right) return;
            int base = findBase(arr, left, right);
            sort(arr, left, base - 1);
            sort(arr, base + 1, right);

        }

        private int findBase(int[] arr, int left, int right) {
            int provid = right;
            int s1 = left;
            for (int s2 = left; s2 < right; s2++) {
                if (arr[s2] < arr[provid]) AriUtils.swap(arr, s1++, s2);
            }
            AriUtils.swap(arr, s1, provid);
            return s1;
        }
    }

    // 归并
    public static class Merger extends Sort02 {

        @Override
        void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private void sort(int[] arr, int left, int right) {

            if (left >= right) return;
            int mid = (left + right) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merger(arr, left, mid, right);

        }

        private void merger(int[] arr, int left, int mid, int right) {

            int s1 = left;
            int s2 = mid + 1;
            int[] temp = new int[right - left + 1];
            int z = 0;
            while (s1 <= mid && s2 <= right) {
                if (arr[s1] < arr[s2]) temp[z++] = arr[s1++];
                else temp[z++] = arr[s2++];
            }
            while (s1 <= mid) temp[z++] = arr[s1++];

            while (s2 <= right) temp[z++] = arr[s2++];

            for (int i = 0; i < temp.length; i++) {
                arr[left + i] = temp[i];
            }

        }
    }

    // 堆排序
    public static class Heap extends Sort02 {

        @Override
        void sort(int[] arr) {
            for (int i = arr.length / 2 - 1; i >= 0; i--) {
                heapFly(arr, arr.length, i);
            }

            for (int i = arr.length - 1; i >= 0; i--) {
                AriUtils.swap(arr, i, 0);
                heapFly(arr, i, 0);
            }
        }

        private void heapFly(int[] arr, int length, int i) {

            int left = i * 2 + 1;
            int right = i * 2 + 2;
            int root = i;

            if (left < length && arr[left] > arr[root]) root = left;

            if (right < length && arr[right] > arr[root]) root = right;

            if (root != i) {

                AriUtils.swap(arr, root, i);
                heapFly(arr, length, root);


            }


        }
    }

    // shell 希尔排序
    public static class Shell extends Sort02 {


        // 减小增量排序
        @Override
        void sort(int[] arr) {

            for (int i = arr.length / 2; i >= 1; i /= 2) {
                int j = i;
                while (j < arr.length) {
                    int n = j - i;
                    int temp = arr[j];
                    while (n >= 0 && arr[n] > temp) {
                        arr[n + i] = arr[n];
                        n -= i;
                    }
                    arr[n + i] = temp;
                    j++;
                }
            }


        }
    }

    // 桶排序
    public static class Bucket extends Sort02 {

        @Override
        void sort(int[] arr) {
            int max = arr[0];

            int min = arr[0];

            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];

                if (min > arr[i]) min = arr[i];
            }

            int size = (max + min) / arr.length + 1;

            int cnt = (max + min) / size + 1;

            List<Integer>[] arrayLists = new ArrayList[cnt];
            for (int i = 0; i < arrayLists.length; i++) {
                arrayLists[i] = new ArrayList<>();
            }
            // 入桶
            for (int i = 0; i < arr.length; i++) {
                arrayLists[arr[i] / size].add(arr[i]);
            }


            for (int i = 0; i < arrayLists.length; i++) {
                arrayLists[i].sort(null);
            }

            int x = 0;

            for (int i = 0; i < arrayLists.length; i++) {
                for (int j = 0; j < arrayLists[i].size(); j++) {
                    arr[x++] = arrayLists[i].get(j);
                }
            }
        }
    }

    // 计数排序
    public static class Count extends Sort02 {

        @Override
        void sort(int[] arr) {
            int max = arr[0];

            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }

            int[] count = new int[max + 1];

            for (int i = 0; i < arr.length; i++) {
                count[arr[i]] += 1;
            }

            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            int x = 0;

            int[] temp = new int[arr.length];

            for (int i = 0; i < arr.length; i++) {
                int z = count[arr[i]] - 1;
                temp[z] = arr[i];
                count[arr[i]] -= 1;
            }


            for (int i = 0; i < temp.length; i++) {
                arr[i] = temp[i];
            }


        }
    }

    // 基数排序
    public static class Cardinal extends Sort02 {

        @Override
        void sort(int[] arr) {

            List<Integer>[] bucket = new ArrayList[10];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList();
            }
            int max = arr[0];

            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }

            int cnt = (max + "").length();

            int j = 0;

            while (j < cnt) {

                // 入桶
                for (int i = 0; i < arr.length; i++) {
                    int z = (int) (arr[i] / Math.pow(10, j) % 10);
                    bucket[z].add(arr[i]);
                }
                int tmp = 0;
                // 出桶
                for (int i = 0; i < bucket.length; i++) {
                    for (int z = 0; z < bucket[i].size(); z++) {
                        arr[tmp++] = bucket[i].get(z);
                    }
                    bucket[i].clear();
                }

                j++;
            }


        }
    }

    public static void sort(Sort02 sort02) {
        int[] arr = AriUtils.randomArr(10);
        sort02.sort(arr);
        AriUtils.print(arr);
    }

    public static void main(String[] args) {
        sort(new Bubble());
        sort(new Insert());
        sort(new Select());
        sort(new Fast());
        sort(new Merger());
        sort(new Heap());
        sort(new Shell());
        sort(new Bucket());
        sort(new Count());
        sort(new Cardinal());


    }


}
