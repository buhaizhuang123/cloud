package ms;

import util.AriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/6/2 08:39
 * @mark OneTime 排序算法
 */
public abstract class OneTime {

    public abstract String getName();

    public abstract void sort(int[] arr);

    // 冒泡排序
    public static class Bubble extends OneTime {

        @Override
        public String getName() {
            return "Bubble";
        }

        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j + 1 < arr.length - i; j++) {
                    if (arr[j] > arr[j + 1]) AriUtils.swap(arr, j, j + 1);
                }
            }
        }
    }

    // 插入排序
    public static class Insert extends OneTime {

        @Override
        public String getName() {
            return "Insert";
        }

        @Override
        public void sort(int[] arr) {
            for (int i = 1; i < arr.length; i++) {

                int j = i - 1;
                int temp = arr[i];
                // 后移
                while (j <= 0 && arr[j] > temp) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = temp;
            }
        }
    }

    // 选择排序
    public static class Select extends OneTime {

        @Override
        public String getName() {
            return "Select";
        }

        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int min = i;
                int j = i + 1;
                while (j < arr.length) {
                    if (arr[j] < arr[min]) min = j;
                    j++;
                }
                AriUtils.swap(arr, min, j);
            }
        }
    }

    // 希尔排序 缩小增量排序
    public static class Shell extends OneTime {

        @Override
        public String getName() {
            return "Shell";
        }

        @Override
        public void sort(int[] arr) {

            for (int i = arr.length / 2; i >= 1; i--) {

                for (int j = i; j < arr.length; j++) {
                    int temp = arr[j];
                    int n = j - i;
                    while (n >= 0 && arr[n] > temp) {
                        arr[n + i] = arr[n];
                        n -= i;
                    }
                    arr[n + i] = temp;
                }
            }


        }
    }

    // 归并排序
    public static class Merger extends OneTime {

        @Override
        public String getName() {
            return "Merger";
        }

        @Override
        public void sort(int[] arr) {
            iterator(arr, 0, arr.length - 1);
        }

        private void iterator(int[] arr, int left, int right) {

            if (left >= right) return;

            int mid = (left + right) / 2;

            iterator(arr, left, mid);
            iterator(arr, mid + 1, right);

            merger(arr, left, mid, right);


        }

        private void merger(int[] arr, int left, int mid, int right) {

            int s1 = left;

            int s2 = mid + 1;

            int[] temp = new int[right - left + 1];
            int i = 0;
            while (s1 <= mid && s2 <= right) {

                if (arr[s1] < arr[s2]) temp[i++] = arr[s1++];
                else temp[i++] = arr[s2++];

            }

            while (s1 <= mid) temp[i++] = arr[s1++];


            while (s2 < right) temp[i++] = arr[s2++];

            for (int j = 0; j < temp.length; j++) {

                arr[left + j] = temp[j];

            }


        }
    }

    // 计数排序
    public static class Count extends OneTime {

        @Override
        public String getName() {
            return "Count";
        }

        @Override
        public void sort(int[] arr) {

            int max = arr[0];

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) max = arr[i];
            }

            int[] count = new int[max + 1];

            for (int i = 0; i < arr.length; i++) {
                count[arr[i]] += 1;
            }
            // 累计数组
            for (int i = 0; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            int[] temp = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                int idx = count[arr[i]] - 1;
                temp[idx] = arr[i];
                count[arr[i]] -= 1;
            }

            for (int i = 0; i < temp.length; i++) {
                arr[i] = temp[i];
            }


        }
    }

    // 基数排序
    public static class Cardinal extends OneTime {

        @Override
        public String getName() {
            return "Cardinal";
        }

        @Override
        public void sort(int[] arr) {
            int max = arr[0];

            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) {
                    max = arr[i];
                }
            }

            int ctx = (max + "").length();

            int j = 0;
            List<Integer>[] buckets = new ArrayList[ctx];

            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new ArrayList<>();
            }

            while (j < ctx) {
                // 入桶
                for (int i = 0; i < arr.length; i++) {
                    int idx = (int) (arr[i] / Math.pow(10, j) % 10);
                    buckets[idx].add(arr[i]);
                }
                int x = 0;
                for (int i = 0; i < buckets.length; i++) {
                    for (int z = 0; z < buckets[i].size(); z++) {
                        arr[x++] = buckets[i].get(z);
                    }
                    buckets[i].clear();
                }


                j++;
            }


        }
    }

    // 堆排序
    public static class Heap extends OneTime {

        @Override
        public String getName() {
            return "Heap";
        }

        @Override
        public void sort(int[] arr) {
            for (int i = arr.length / 2 - 1; i < arr.length; i++) {

                heapFly(arr, arr.length, i);

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

    // 桶排序
    public static class Bucket extends OneTime {

        @Override
        public String getName() {
            return "";
        }

        @Override
        public void sort(int[] arr) {

        }
    }

    // 快速排序
    public static class Fast extends OneTime {

        @Override
        public String getName() {
            return "";
        }

        @Override
        public void sort(int[] arr) {

        }
    }


}
