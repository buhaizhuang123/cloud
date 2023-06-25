package ms;

import util.AriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/6/4 11:22
 * @mark SortMath
 */
public abstract class SortMath {


    public abstract String getName();

    public abstract void sort(int[] arr);

    // 冒泡
    public static class Bubble extends SortMath {
        @Override
        public String getName() {
            return "Bubble";
        }

        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j + 1 < arr.length - 1; j++) {
                    if (arr[j] > arr[j + 1]) AriUtils.swap(arr, j, j + 1);
                }
            }
        }
    }

    // 插入
    public static class Insert extends SortMath {
        @Override
        public String getName() {
            return "Insert";
        }

        @Override
        public void sort(int[] arr) {
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
    public static class Select extends SortMath {
        @Override
        public String getName() {
            return "Select";
        }

        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int min = arr[i];
                int j = i + 1;
                int z = i;
                while (j < arr.length) {
                    if (min > arr[j]) {
                        min = arr[j];
                        z = j;
                    }
                    j++;
                }

                AriUtils.swap(arr, z, i);


            }
        }
    }

    // 快速
    public static class Fast extends SortMath {

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
            int base = right;
            int s1 = left;
            for (int i = left; i < right; i++) {
                if (arr[i] < arr[base]) AriUtils.swap(arr, s1++, i);
            }
            AriUtils.swap(arr, s1, base);
            return s1;
        }
    }

    // 堆排序
    public static class Heap extends SortMath {
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

        private void heapFly(int[] arr, int length, int i) {

            int left = i * 2 + 1;

            int right = i * 2 + 2;
            int root = i;
            if (left < length && arr[left] > arr[root]) root = left;

            if (right < length && arr[right] > arr[root]) root = right;
            if (root != i) {
                AriUtils.swap(arr, i, root);
                heapFly(arr, length, root);
            }

        }
    }

    // 桶排序
    public static class Bucket extends SortMath {
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

            int size = (max - min) / arr.length + 1;

            int cnt = (max - min) / size + 1;

            List<Integer>[] buckets = new ArrayList[cnt];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new ArrayList<>();
            }

            // 入桶
            for (int i = 0; i < arr.length; i++) {
                int idx = (arr[i] - min) / size;
                buckets[idx].add(arr[i]);
            }

            for (int i = 0; i < buckets.length; i++) {
                buckets[i].sort(null);
            }

            int t = 0;
            for (int i = 0; i < buckets.length; i++) {
                for (Integer value : buckets[i]) {
                    arr[t++] = value;
                }
            }


        }
    }

    // 计数排序
    public static class Count extends SortMath {
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

            for (int i = 0; i < arr.length; i++) {
                count[arr[i]] += 1;
            }

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
    public static class Cardinal extends SortMath {


        @Override
        public String getName() {
            return "Cardinal";
        }

        @Override
        public void sort(int[] arr) {


            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }

            int ctx = (max + "").length();

            List<Integer>[] bucket = new ArrayList[10];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList<>();
            }

            int i = 0;
            while (i <= ctx) {

                //、入桶
                for (int j = 0; j < arr.length; j++) {

                    int idx = (int) (arr[j] / Math.pow(10, i) % 10);
                    bucket[idx].add(arr[j]);
                }
                int m = 0;
                // 出桶
                for (int z = 0; z < bucket.length; z++) {

                    for (int x = 0; x < bucket[z].size(); x++) {
                        arr[m++] = bucket[z].get(x);
                    }
                    bucket[z].clear();
                }


                i++;
            }


        }
    }

    // 归并排序
    public static class Merger extends SortMath {
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
            int[] temp = new int[right - left + 1];
            int i = 0;
            while (s1 <= mid && s2 <= right) {

                if (arr[s1] < arr[s2]) temp[i++] = arr[s1++];
                else temp[i++] = arr[s2++];

            }

            while (s1 <= mid) temp[i++] = arr[s1++];

            while (s2 <= right) temp[i++] = arr[s2++];

            for (int z = 0; z < temp.length; z++) {
                arr[left + z] = temp[z];


            }

        }
    }

    // 希尔排序 shell
    public static class Shell extends SortMath {
        @Override
        public String getName() {
            return "Shell";
        }

        @Override
        public void sort(int[] arr) {

            for (int i = arr.length / 2; i >= 1; i /= 2) {
                int j = i;

                while (j < arr.length) {
                    int temp = arr[j];
                    int n = j - i;
                    while (n >= 0 && arr[n] > arr[j]) {
                        arr[n + i] = arr[n];
                        n -= i;
                    }
                    arr[n + i] = temp;
                    j++;
                }

            }


        }
    }


    public static void sort(SortMath sortMath) {
        int[] arr = AriUtils.randomArr(10);
        System.out.println("sortMath.getName() = " + sortMath.getName());
        sortMath.sort(arr);
        AriUtils.print(arr);
    }


    public static void main(String[] args) {

        SortMath.sort(new SortMath.Bubble());
        SortMath.sort(new SortMath.Insert());
        SortMath.sort(new SortMath.Select());
        SortMath.sort(new SortMath.Fast());
        SortMath.sort(new SortMath.Heap());
        SortMath.sort(new SortMath.Bucket());
        SortMath.sort(new SortMath.Count());
        SortMath.sort(new SortMath.Cardinal());
        SortMath.sort(new SortMath.Merger());
        SortMath.sort(new SortMath.Shell());


    }

}
