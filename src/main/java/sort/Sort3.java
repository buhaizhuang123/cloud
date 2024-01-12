package sort;

import util.AriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/7/15 08:22
 * @mark Sort3
 */
public abstract class Sort3 {

    public abstract void sort(int[] arr);


    // 冒泡
    public static class Bubble extends Sort3 {
        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int j = 0;
                while (j + 1 < arr.length - i) {
                    if (arr[j] > arr[j + 1]) AriUtils.swap(arr, j, j + 1);
                    j++;
                }
            }
        }
    }

    // 选择
    public static class Select extends Sort3 {
        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int min = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[min] > arr[j]) {
                        min = j;
                    }
                }
                AriUtils.swap(arr, min, i);
            }
        }
    }

    // 插入
    public static class Insert extends Sort3 {
        @Override
        public void sort(int[] arr) {
            for (int i = 1; i < arr.length; i++) {
                int j = i - 1;
                int temp = arr[i];
                while (j >= 0 && temp < arr[j]) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = temp;
            }
        }
    }

    // 希尔排序 shell 排序 减小增量排序
    public static class Shell extends Sort3 {
        @Override
        public void sort(int[] arr) {
            for (int i = arr.length / 2; i >= 1; i /= 2) {
                int j = i;
                while (j < arr.length) {

                    int n = j - i;
                    int temp = arr[j];
                    while (n >= 0 && temp < arr[n]) {
                        arr[n+i] = arr[n];
                        n -= i;
                    }
                    arr[n + i] = temp;
                    j++;
                }

            }
        }
    }

    // 快速
    public static class Fast extends Sort3 {
        @Override
        public void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private void sort(int[] arr, int left, int right) {
            if (left >= right) return;
            int mid = findPri(arr, left, right);
            sort(arr, left, mid - 1);
            sort(arr, mid + 1, right);
        }

        private int findPri(int[] arr, int left, int right) {
            int provid = right;

            int s1 = left;
            for (int i = left; i < right; i++) {
                if (arr[i] < arr[provid]) AriUtils.swap(arr, s1++, i);
            }
            AriUtils.swap(arr, s1, provid);
            return s1;
        }
    }

    // 归并
    public static class Merger extends Sort3 {
        @Override
        public void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private void sort(int[] arr, int left, int right) {
            if (left >= right) {
                return;
            }


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
                if (arr[s1] <= arr[s2]) {
                    temp[z++] = arr[s1++];
                } else {
                    temp[z++] = arr[s2++];
                }
            }

            while (s1 <= mid) {
                temp[z++] = arr[s1++];
            }

            while (s2 <= right) temp[z++] = arr[s2++];

            for (int i = 0; i < temp.length; i++) {
                arr[left + i] = temp[i];
            }
        }
    }

    // 堆排序
    public static class Heap extends Sort3 {
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

        public void heapFly(int[] arr, int l, int i) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;
            int root = i;
            if (left < l && arr[root] < arr[left]) {
                root = left;
            }

            if (right < l && arr[root] < arr[right]) {
                root = right;
            }

            if (root != i) {
                AriUtils.swap(arr, root, i);
                heapFly(arr, l, root);
            }


        }

    }

    // 桶排序
    public static class Bucket extends Sort3 {
        @Override
        public void sort(int[] arr) {
            int min = arr[0];
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (min > arr[i]) min = arr[i];
                if (max < arr[i]) max = arr[i];
            }

            int size = (max - min) / arr.length + 1;

            int count = (max - min) / size + 1;
            List<Integer>[] bucket = new ArrayList[count];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList();
            }

            // 入桶
            for (int i = 0; i < arr.length; i++) {
                int idx = (arr[i] - min) / size;
                bucket[idx].add(arr[i]);
            }

            // 桶内元素排序
            for (int i = 0; i < bucket.length; i++) {
                bucket[i].sort(null);
            }

            // 出桶
            int init = 0;
            for (int i = 0; i < bucket.length; i++) {
                for (int j = 0; j < bucket[i].size(); j++) {
                    arr[init++] = bucket[i].get(j);
                }
            }

        }
    }

    // 计数排序
    public static class Count extends Sort3 {
        @Override
        public void sort(int[] arr) {

            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }

            int[] bucket = new int[max + 1];
            for (int i = 0; i < arr.length; i++) {
                bucket[arr[i]] += 1;
            }

            for (int i = 1; i < bucket.length; i++) {
                bucket[i] += bucket[i - 1];
            }
            int[] temp = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                int idx = bucket[arr[i]] - 1;
                temp[idx] = arr[i];
                bucket[arr[i]] -= 1;
            }

            for (int i = 0; i < temp.length; i++) {
                arr[i] = temp[i];
            }


        }
    }

    // 基数排序
    public static class Cardinal extends Sort3 {
        @Override
        public void sort(int[] arr) {
            int max = arr[0];

            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }
            int cnt = (max + "").length();
            ArrayList<Integer>[] bucket = new ArrayList[10];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList();
            }
            int j = 0;
            while (j <= cnt) {
                // 入桶
                for (int i = 0; i < arr.length; i++) {
                    int c = (int) (arr[i] / Math.pow(10, j) % 10);
                    bucket[c].add(arr[i]);
                }
                int init = 0;
                // 出桶
                for (int i = 0; i < bucket.length; i++) {
                    for (Integer integer : bucket[i]) {
                        arr[init++] = integer;
                    }
                    bucket[i].clear();
                }
                j++;
            }


        }
    }


    public static void print(Sort3 sort3) {
        int[] arr = AriUtils.randomArr(10);
        sort3.sort(arr);
        AriUtils.print(arr);
    }

    public static void main(String[] args) {
        print(new Bubble());
        print(new Bucket());
        print(new Insert());
        print(new Select());
        print(new Shell());
        print(new Merger());
        print(new Heap());
        print(new Bucket());
        print(new Count());
        print(new Cardinal());
    }

}


