package ms;

import util.AriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/5/24 16:38
 * @mark Sort1
 */
public class Sort1 {

    // 冒泡排序
    public static class BubbleSort {

        public void sort(int[] arr) {

            for (int i = 0; i < arr.length; i++) {

                for (int j = 0; j + 1 < arr.length - i; j++) {
                    if (arr[j] > arr[j + 1]) AriUtils.swap(arr, j, j + 1);
                }

            }

        }

    }

    // 插入排序
    public static class Insert {

        public void sort(int[] arr) {

            for (int i = 1; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - 1;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = temp;
            }
        }

    }

    // 选择排序
    public static class SelectSort {

        public void sort(int[] arr) {

            for (int i = 0; i < arr.length; i++) {

                for (int j = i + 1; j < arr.length; j++) {

                    if (arr[i] > arr[j]) AriUtils.swap(arr, i, j);

                }

            }
        }


    }

    // 堆排序
    public static class HeapSort {

        public void heapFly(int[] arr, int n, int i) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;
            int root = i;
            if (left < n && arr[left] > arr[root]) root = left;

            if (right < n && arr[right] > arr[root]) root = right;

            if (root != i) {
                AriUtils.swap(arr, i, root);
                heapFly(arr, n, root);
            }
        }

        public void sort(int[] arr) {
            // 维护堆的性质
            for (int i = arr.length / 2 - 1; i >= 0; i--) {
                heapFly(arr, arr.length, i);
            }
            // 堆排序
            for (int i = arr.length - 1; i >= 0; i--) {
                AriUtils.swap(arr, i, 0);
                heapFly(arr, i, 0);
            }

        }


    }

    // 快速排序
    public static class FastSort {

        public int findMid(int[] arr, int left, int right) {
            int provid = right;
            int s1 = left;
            for (int i = left; i < arr.length; i++) {
                if (arr[i] < arr[provid]) AriUtils.swap(arr, i, s1++);
            }
            AriUtils.swap(arr, s1, provid);
            return s1;
        }


        public void sort(int[] arr, int left, int right) {
            if (left >= right) return;
            int mid = findMid(arr, left, right);

            sort(arr, left, mid - 1);
            sort(arr, mid + 1, right);

        }

    }


    // 希尔排序
    public static class ShellSort {

        public void sort(int[] arr) {
            // 增量值
            for (int i = arr.length / 2; i >= 1; i--) {

                for (int j = i; j < arr.length; j++) {
                    int temp = arr[j];
                    int n = j - i;
                    while (n >= 0 && arr[n] > temp) {
                        arr[j] = arr[n];
                        n -= i;
                    }
                    arr[n + i] = temp;
                }

            }

        }

    }


    // 桶排序
    public static class BucketSort {

        public void sort(int[] arr) {
            int max = arr[0];
            int min = arr[0];
            int n = arr.length;
            for (int i = 1; i < arr.length; i++) {

                if (max < arr[i]) max = arr[i];

                if (min > arr[i]) min = arr[i];

            }
            // 桶
            int size = (max - min) / n + 1;
            int cnt = (max - min) / size + 1;

            List<Integer>[] buckets = new ArrayList[size];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new ArrayList<>();
            }
            // 将值放入桶中
            for (int i = 0; i < arr.length; i++) {
                buckets[(arr[i] - min) / cnt].add(arr[i]);
            }

            // 将桶内值排序
            for (int i = 0; i < buckets.length; i++) {
                buckets[i].sort(null);
            }
            int idx = 0;
            // 将桶内值放到原有队列
            for (int i = 0; i < buckets.length; i++) {
                for (int i1 = 0; i1 < buckets[i].size(); i1++) {
                    arr[idx++] = buckets[i].get(i1);
                }
            }

        }

    }

    // 归并排序
    public static class MergerSort {

        public void sort(int[] arr, int left, int right) {

            if (left >= right) return;
            int mid = (left + right) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merger(arr, left, mid, right);

        }

        private void merger(int[] arr, int left, int mid, int right) {

            int s1 = left;
            int s2 = mid + 1;
            int idx = 0;
            int[] temp = new int[right - left + 1];
            while (s1 <= mid && s2 <= right) {
                if (arr[s1] < arr[s2]) {
                    temp[idx++] = arr[s1++];
                } else {
                    temp[idx++] = arr[s2++];
                }
            }

            while (s1 <= mid) temp[idx++] = arr[s1++];

            while (s2 <= right) temp[idx++] = arr[s2++];

            for (int i = 0; i < temp.length; i++) {

                arr[left + i] = temp[i];

            }

        }

    }


    // 计数排序
    public static class CountSort {

        public void sort(int[] arr) {

            int max = arr[0];

            for (int i = 0; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }

            int[] count = new int[max + 1];

            // 计数队列
            for (int i = 0; i < arr.length; i++) {
                count[arr[i]] += 1;
            }

            // 累计队列
            for (int i = 1; i < max + 1; i++) {
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
    public static class Cardinal {

        public void sort(int[] arr) {

            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }
            int cnt = (max + "").length();

            int j = 0;
            List<Integer>[] buckets = new ArrayList[10];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new ArrayList<>();
            }
            while (j <= cnt) {
                // 入桶
                for (int i = 0; i < arr.length; i++) {

                    int idx = (int) (arr[i] / Math.pow(10, j) % 10);
                    buckets[idx].add(arr[i]);

                }
                int x = 0;
                // 出桶
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

    public static void main(String[] args) {
        int[] arr = AriUtils.randomArr(10);
//        new BubbleSort().sort(arr);
//        new Insert().sort(arr);
//        new SelectSort().sort(arr);
//        new HeapSort().sort(arr);
//        new FastSort().sort(arr, 0, arr.length - 1);
//        new ShellSort().sort(arr);
//        new BucketSort().sort(arr);
//        new MergerSort().sort(arr, 0, arr.length - 1);
        new Cardinal().sort(arr);
        AriUtils.print(arr);


    }

}
