package exercise;

import util.AriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/6/9 14:46
 * @mark Exercise 基本排序 10 大
 */
public abstract class Exercise {

    public abstract String getName();

    public abstract void sort(int[] arr);

    // 冒泡排序
    public static class Bubble extends Exercise {
        @Override
        public String getName() {
            return "Bubble";
        }

        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                // 与低位元素比较、高值向后移、每次循环、都可以确定当前数组中最大的值
                for (int j = 0; j + 1 < arr.length - i; j++) {
                    if (arr[j + 1] < arr[j]) AriUtils.swap(arr, j + 1, j);
                }
            }
        }
    }


    // 插入排序
    public static class Insert extends Exercise {
        @Override
        public String getName() {
            return "Insert";
        }

        @Override
        public void sort(int[] arr) {
            // 插入排序 低位元素(后置)、与高位元素(前置)比较、 满足条件前置元素后移
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

    // 选择排序
    public static class Select extends Exercise {
        @Override
        public String getName() {
            return "Select";
        }

        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int min = i;
                for (int z = i + 1; z < arr.length; z++) {
                    if (arr[z] < arr[min]) min = z;
                }
                AriUtils.swap(arr, min, i);
            }
        }
    }

    // 快速排序
    public static class Fast extends Exercise {
        @Override
        public String getName() {
            return "Fast";
        }

        @Override
        public void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private void sort(int[] arr, int left, int right) {
            if (left >= right) return;
            int base = findBase(arr, left, right);
            sort(arr, left, base - 1);
            sort(arr, base + 1, right);

        }

        private int findBase(int[] arr, int left, int right) {
            int pro = right;

            int s1 = left;

            for (int i = left; i < right; i++) {
                if (arr[i] <= arr[pro]) AriUtils.swap(arr, s1++, i);
            }
            AriUtils.swap(arr, s1, pro);
            return s1;
        }
    }

    // 希尔排序 shell
    public static class Shell extends Exercise {
        @Override
        public String getName() {
            return "Shell";
        }

        @Override
        public void sort(int[] arr) {
            for (int i = arr.length / 2; i >= 1; i--) {

                for (int j = i; j < arr.length; j++) {
                    int n = j - i;
                    int temp = arr[j];
                    while (n >= 0 && arr[n] > temp) {
                        arr[n + i] = arr[n];
                        n -= i;
                    }
                    arr[n + i] = temp;
                }


            }
        }
    }

    // 堆排序
    public static class Heap extends Exercise {
        @Override
        public String getName() {
            return "Heap";
        }

        @Override
        public void sort(int[] arr) {

            for (int i = arr.length / 2 - 1; i >= 0; i--) {
                heapFly(arr, arr.length, i);
            }

            for (int i = arr.length - 1; i >= 0; i--) {
                AriUtils.swap(arr, i, 0);
                heapFly(arr, i, 0);
            }


        }

        private void heapFly(int[] arr, int n, int i) {

            int left = i * 2 + 1;

            int right = i * 2 + 2;

            int root = i;

            if (left < n && arr[left] > arr[root]) root = left;

            if (right < n && arr[right] > arr[root]) root = right;

            if (root != i) {
                AriUtils.swap(arr, root, i);
                heapFly(arr, n, root);
            }


        }
    }

    // 桶排序
    public static class Bucket extends Exercise {
        @Override
        public String getName() {
            return "Bucket";
        }

        @Override
        public void sort(int[] arr) {
            int max = arr[0];
            int min = arr[0];
            for (int i = 1; i < arr.length; i++) {

                if (max < arr[i]) max = arr[i];
                if (min > arr[i]) min = arr[i];

            }
            int size = (max - min) / arr.length + 1; // 桶元素大小
            int ctn = (max - min) / size + 1;// 桶数量

            List<Integer>[] buckets = new ArrayList[ctn];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new ArrayList<>();
            }


            // 入桶
            for (int i = 0; i < arr.length; i++) {
                int idx = (arr[i] - min) / size;
                buckets[idx].add(arr[i]);
            }

            // 桶内元素排序
            for (int i = 0; i < buckets.length; i++) {
                buckets[i].sort(null);
            }
            int t = 0;
            // 将桶内元素取出
            for (int i = 0; i < buckets.length; i++) {
                for (int z = 0; z < buckets[i].size(); z++) {
                    arr[t++] = buckets[i].get(z);
                }
            }


        }
    }

    // 计数排序
    public static class Count extends Exercise {
        @Override
        public String getName() {
            return "Count";
        }

        @Override
        public void sort(int[] arr) {
            int max = arr[0];

            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }

            int[] count = new int[max + 1];
            // 计数
            for (int i = 0; i < arr.length; i++) {
                count[arr[i]] += 1;
            }

            // 累计
            for (int i = 1; i < count.length; i++) {
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
    public static class Cardinal extends Exercise {
        @Override
        public String getName() {
            return "Cardinal";
        }

        @Override
        public void sort(int[] arr) {
            // 桶元素
            List<Integer>[] buckets = new ArrayList[10];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new ArrayList<>();
            }
            int max = arr[0];

            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }

            int ctn = (max + "").length();
            int j = 0;
            while (j <= ctn) {
                // 入桶
                for (int i = 0; i < arr.length; i++) {
                    int idx = (int) (arr[i] / Math.pow(10, j) % 10);
                    buckets[idx].add(arr[i]);
                }
                int x = 0;
                // 出桶
                for (int i = 0; i < buckets.length; i++) {
                    for (int i1 = 0; i1 < buckets[i].size(); i1++) {
                        arr[x++] = buckets[i].get(i1);
                    }
                    buckets[i].clear();
                }
                j++;
            }


        }
    }

    // 归并排序
    public static class Merger extends Exercise {
        @Override
        public String getName() {
            return "Merger";
        }

        @Override
        public void sort(int[] arr) {
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

            int zero = 0;
            int[] temp = new int[right - left + 1];

            while (s1 <= mid && s2 <= right) {
                if (arr[s1] < arr[s2]) temp[zero++] = arr[s1++];
                else temp[zero++] = arr[s2++];
            }

            while (s1 <= mid) temp[zero++] = arr[s1++];
            while (s2 <= right) temp[zero++] = arr[s2++];
            for (int i = 0; i < temp.length; i++) {
                arr[left + i] = temp[i];
            }

        }
    }


    public static void sort(Exercise exercise) {
        int[] arr = AriUtils.randomArr(10);
        System.out.println(exercise.getName());
        exercise.sort(arr);
        AriUtils.print(arr);
    }

    public static void main(String[] args) {
        sort(new Bubble());
        sort(new Select());
        sort(new Insert());
        sort(new Bucket());
        sort(new Merger());
        sort(new Fast());
        sort(new Count());
        sort(new Cardinal());
        sort(new Shell());
        sort(new Heap());

    }

}
