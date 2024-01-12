package ms;

import util.AriUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/5/23 12:01
 * @mark Sort 排序
 */
public class Sort {

    /**
     * 计数排序
     */
    public static class SumSort {


        public void sort(int[] arr) {
            // 获取counter数组大小
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) max = arr[i];
            }

            int[] counter = new int[max + 1];

            for (int i = 0; i < arr.length; i++) {
                counter[arr[i]] += 1;
            }

            // 累计数组
            for (int i = 1; i < counter.length; i++) {
                counter[i] += counter[i - 1];
            }

            int[] temp = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                int index = counter[arr[i]] - 1;
                temp[index] = arr[i];
                counter[arr[i]] -= 1;
            }

            for (int i = 0; i < temp.length; i++) {

                arr[i] = temp[i];

            }
        }

    }

    /**
     * 堆排序
     */
    public static class Heap {

        public void heapFly(int[] arr, int n, int i) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;
            int root = i;

            if (left < n && arr[root] < arr[left]) root = left;

            if (right < n && arr[root] < arr[right]) root = right;

            if (root != i) {
                AriUtils.swap(arr, root, i);
                heapFly(arr, n, root);
            }


        }

    }

    /**
     * 冒泡排序
     */
    public static class Bubble {
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                for (int k = 0; k + 1 < arr.length - i; k++) {
                    if (arr[k] > arr[k + 1]) {
                        AriUtils.swap(arr, k, k + 1);
                    }

                }
            }

        }
    }

    /**
     * 插入排序
     */
    public static class Insert {

        public void sort(int[] arr) {

            for (int i = 1; i < arr.length; i++) {
                int temp = arr[i];
                int i1 = i - 1;
                for (; i1 >= 0 && arr[i1] > temp; i1--) {
                    arr[i1 + 1] = arr[i1];
                }
                arr[i1 + 1] = temp;
            }

        }


    }

    /**
     * 归并排序
     */
    public static class Merger {

        public void split(int[] arr, int left, int right) {
            if (left >= right) {
                return;
            }
            int mid = (left + right) / 2;
            split(arr, left, mid);
            split(arr, mid + 1, right);
            merger(arr, left, mid, right);


        }

        private void merger(int[] arr, int left, int mid, int right) {

            int s1 = left;
            int s2 = mid + 1;
            int i = 0;
            int[] temp = new int[right - left + 1];

            while (s1 <= mid && s2 <= right) {
                if (arr[s1] <= arr[s2]) {
                    temp[i++] = arr[s1++];
                } else {
                    temp[i++] = arr[s2++];
                }
            }

            while (s1 <= mid) {
                temp[i++] = arr[s1++];
            }

            while (s2 <= right) temp[i++] = arr[s2++];

            for (int i1 = 0; i1 < temp.length; i1++) {
                arr[left + i1] = temp[i1];
            }

        }

    }

    /**
     * 快速排序
     */
    public static class FastSort {

        public void sort(int[] arr, int left, int right) {
            if (left >= right) return;
            int mid = part(arr, left, right);
            sort(arr, left, mid - 1);
            sort(arr, mid + 1, right);
        }

        private int part(int[] arr, int left, int right) {
            int provid = right; // 基点
            int i = left;
            for (int j = left; j < arr.length; j++) {
                if (arr[j] < arr[provid]) AriUtils.swap(arr, j, i++);
            }
            AriUtils.swap(arr, i, provid);
            return i;
        }

    }

    /**
     * 希尔排序
     */
    public static class ShellSort {

        public void sort(int[] arr) {
            // 增量
            for (int i = arr.length / 2; i > 0; i--) {
                // 保证增量元素之间有序
                for (int j = i; j < arr.length; j++) {
                    int n = j - i; // 比较元素
                    int temp = arr[j]; // 比较值
                    while (n >= 0 && arr[n] > temp) {
                        arr[j] = arr[n];
                        n -= i;
                    }
                    arr[n + i] = temp;
                }
            }
        }
    }

    /**
     * 桶排序
     */
    public static class BucketSort {

        public void sort(int[] arr) {
            int n = arr.length;
            int max = arr[0];
            int min = arr[0];
            for (int i = 1; i < arr.length; i++) {

                if (max < arr[i]) max = arr[i];

                if (min > arr[i]) min = arr[i];
            }
            // 桶大小
            int size = (max - min) / n + 1;

            // 桶数量
            int cnt = (max - min) / size + 1;

            List<Integer>[] list = new ArrayList[cnt];

            for (int i = 0; i < cnt; i++) {
                list[i] = new ArrayList<>();
            }

            // 将数据放入桶对象中
            for (int i = 0; i < arr.length; i++) {
                int idx = (arr[i] - min) / size;
                list[idx].add(arr[i]);
            }
            // 分别对桶对象中数据进行排序
            for (int i = 0; i < cnt; i++) {
                list[i].sort(null);
            }
            int index = 0;
            // 将桶中数据遍历放入原队列中
            for (int i = 0; i < cnt; i++) {
                for (int i1 = 0; i1 < list[i].size(); i1++) {
                    arr[index++] = list[i].get(i1);
                }
            }

        }

    }


    /**
     * 计数排序
     */
    public static void sumSort() {
        int[] arr = AriUtils.randomArr(10);

        new SumSort().sort(arr);
        System.out.println("计数排序");
        AriUtils.print(arr);
    }


    /**
     * 堆排序
     */
    public static void heapSort() {
        int[] arr = AriUtils.randomArr(10);

        Heap heap = new Heap();

        int n = arr.length;
        // 保证堆结构 大顶堆 root 最大、小顶堆 root 最小
        for (int i = n / 2 - 1; i >= 0; i--) {
            heap.heapFly(arr, n, i);
        }

        // 堆排序
        for (int i = n - 1; i >= 0; i--) {
            AriUtils.swap(arr, i, 0);
            heap.heapFly(arr, i, 0);
        }

        System.out.println("堆排序");
        AriUtils.print(arr);
    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort() {
        int[] arr = AriUtils.randomArr(10);
        new Bubble().sort(arr);
        System.out.println("冒泡排序");
        AriUtils.print(
                arr
        );

    }

    /**
     * 插入排序
     */
    public static void insertSort() {

        int[] arr = AriUtils.randomArr(10);

        new Insert().sort(arr);
        System.out.println("插入排序");
        AriUtils.print(arr);


    }

    /**
     * 归并排序
     */
    public static void mergerSort() {

        int[] arr = AriUtils.randomArr(10);
        new Merger().split(arr, 0, arr.length - 1);
        System.out.println("归并排序");
        AriUtils.print(arr);
    }

    /**
     * 快速排序
     */
    public static void fastSort() {

        int[] arr = AriUtils.randomArr(10);
        new FastSort().sort(arr, 0, arr.length - 1);
        System.out.println("快速排序");
        AriUtils.print(arr);

    }

    /**
     * 希尔排序
     */
    public static void shellSort() {

        int[] arr = AriUtils.randomArr(10);
        new ShellSort().sort(arr);
        System.out.println("希尔排序");
        AriUtils.print(arr);

    }


    public static void bucketSort() {
        int[] arr = AriUtils.randomArr(10);
        new BucketSort().sort(arr);
        System.out.println("桶排序");
        AriUtils.print(arr);
    }


    public static void main(String[] args) {
        sumSort();
        heapSort();
        bubbleSort();
        insertSort();
        mergerSort();
        fastSort();
        shellSort();
        bucketSort();
    }

}
