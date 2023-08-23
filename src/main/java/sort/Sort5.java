package sort;

import util.AriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/8/8 08:58
 * @mark Sort5
 */
public abstract class Sort5 {

    public String name;

    public abstract void sort(int[] arr);


    public static void sort(Sort5 sort5) {
        int[] arr = AriUtils.randomArr(10);
        sort5.sort(arr);
        AriUtils.print(arr);
    }

    // 冒泡排序
    public static class Bubble extends Sort5 {

        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {

                for (int j = 0; j + 1 < arr.length - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        AriUtils.swap(arr, j, j + 1);
                    }
                }

            }
        }


    }

    // 插入排序
    public static class Insert extends Sort5 {

        @Override
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
    public static class Select extends Sort5 {

        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int temp = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[temp] > arr[j]) {
                        temp = j;
                    }
                }
                AriUtils.swap(arr, temp, i);
            }
        }

    }

    // Heap 排序 堆
    public static class Heap extends Sort5 {

        @Override
        public void sort(int[] arr) {

            for (int i = arr.length / 2 + 1; i >= 0; i--) {
                heapFly(arr, arr.length, i);
            }

            for (int i = arr.length - 1; i >= 0; i--) {
                AriUtils.swap(arr, i, 0);
                heapFly(arr, i, 0);
            }


        }

        public void heapFly(int[] arr, int len, int i) {
            int left = i * 2 + 1;
            int right = i * 2 + 2;
            int root = i;

            if (left < len && arr[root] < arr[left]) {
                root = left;
            }

            if (right < len && arr[root] < arr[right]) {
                root = right;
            }

            if (root != i) {
                AriUtils.swap(arr, root, i);
                heapFly(arr, len, root);
            }
        }

    }

    // 希尔排序
    public static class Shell extends Sort5 {

        @Override
        public void sort(int[] arr) {
            for (int i = arr.length / 2; i >= 1; i /= 2) {

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

    // 归并排序
    public static class Merge extends Sort5 {

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
            int t = 0;
            int[] temp = new int[right - left + 1];
            while (s1 <= mid && s2 <= right) {
                if (arr[s1] <= arr[s2]) {
                    temp[t++] = arr[s1++];
                } else {
                    temp[t++] = arr[s2++];
                }
            }


            while (s1 <= mid) {
                temp[t++] = arr[s1++];
            }

            while (s2 <= right) {
                temp[t++] = arr[s2++];
            }


            for (int i = 0; i < temp.length; i++) {
                arr[left + i] = temp[i];
            }


        }

    }

    // 桶排序
    public static class Bucket extends Sort5 {

        @Override
        public void sort(int[] arr) {

            int max = arr[0];
            int min = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) max = arr[i];
                if (arr[i] < min) min = arr[i];
            }

            int size = (max + min) / arr.length + 1;
            int cnt = (max + min) / size + 1;

            List<Integer>[] bucket = new ArrayList[cnt];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList<>();
            }

            for (int i = 0; i < arr.length; i++) {
                int idx = arr[i] / size;
                bucket[idx].add(arr[i]);
            }

            for (int i = 0; i < bucket.length; i++) {
                bucket[i].sort(null);
            }

            int temp = 0;
            for (int i = 0; i < bucket.length; i++) {
                for (int j = 0; j < bucket[i].size(); j++) {
                    arr[temp++] = bucket[i].get(j);
                }
            }


        }


    }

    // 计数排序
    public static class Count extends Sort5 {

        @Override
        public void sort(int[] arr) {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }

            int[] bucket = new int[max + 1];
            // 计数
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
    public static class Cardinal extends Sort5 {

        @Override
        public void sort(int[] arr) {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }
            List<Integer>[] bucket = new ArrayList[10];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList<>();
            }
            int cnt = (max + "").length();

            int j = 0;
            while (j <= cnt) {
                // 入桶
                for (int i = 0; i < arr.length; i++) {
                    int z = (int) (arr[i] / Math.pow(10, j) % 10);
                    bucket[z].add(arr[i]);
                }
                int d = 0;
                // 出桶
                for (int i = 0; i < bucket.length; i++) {
                    for (int z = 0; z < bucket[i].size(); z++) {
                        arr[d++] = bucket[i].get(z);
                    }
                    bucket[i].clear();
                }
                j++;
            }


        }

    }

    // 快速排序
    public static class Fast extends Sort5 {

        @Override
        public void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private void sort(int[] arr, int left, int right) {
            if (left >= right) return;
            int mark = findMark(arr, left, right);
            sort(arr, left, mark - 1);
            sort(arr, mark + 1, right);

        }

        private int findMark(int[] arr, int left, int right) {
            int mark = right;
            int s1 = left;
            for (int i = left; i < right; i++) {
                if (arr[i] <= arr[mark]) {
                    AriUtils.swap(arr, s1++, i);
                }
            }
            AriUtils.swap(arr, s1, mark);
            return s1;
        }


    }

    public static void main(String[] args) {
        sort(new Bubble());
        sort(new Insert());
        sort(new Select());
        sort(new Heap());
        sort(new Shell());
        sort(new Merge());
        sort(new Bucket());
        sort(new Count());
        sort(new Cardinal());
        sort(new Fast());
    }


}
