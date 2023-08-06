package sort;

import com.sun.tools.javac.util.ArrayUtils;
import util.AriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/7/23 08:18
 * @mark Sort04
 */
public abstract class Sort04 {

    public abstract void sort(int[] arr);

    public static void sort(Sort04 sort04) {

        int[] arr = AriUtils.randomArr(10);
        sort04.sort(arr);
        AriUtils.print(arr);
    }

    // 冒泡排序
    public static class Bubble extends Sort04 {


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
    public static class Insert extends Sort04 {

        @Override
        public void sort(int[] arr) {

            for (int i = 1; i < arr.length; i++) {
                int z = i - 1;
                int temp = arr[i];
                while (z >= 0 && arr[z] > temp) {
                    arr[z + 1] = arr[z];
                    z--;
                }
                arr[z + 1] = temp;
            }

        }
    }

    // 选择排序
    public static class Select extends Sort04 {


        @Override
        public void sort(int[] arr) {
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

    // 归并排序
    public static class Merger extends Sort04 {

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
            int d = 0;

            while (s1 <= mid && s2 <= right) {
                if (arr[s1] < arr[s2]) {
                    temp[d++] = arr[s1++];
                } else {
                    temp[d++] = arr[s2++];
                }
            }
            while (s1 <= mid) temp[d++] = arr[s1++];
            while (s2 <= right) temp[d++] = arr[s2++];
            for (int i = 0; i < temp.length; i++) {
                arr[left + i] = temp[i];
            }

        }

    }


    // 快速排序
    public static class Fast extends Sort04 {

        @Override
        public void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);
        }

        private void sort(int[] arr, int left, int right) {

            if (left >= right) return;
            int pro = findPro(arr, left, right);
            sort(arr, left, pro - 1);
            sort(arr, pro + 1, right);
        }

        private int findPro(int[] arr, int left, int right) {
            int provid = right;
            int s1 = left;
            for (int s2 = left; s2 < right; s2++) {
                if (arr[s2] <= arr[provid]) AriUtils.swap(arr, s1++, s2);
            }
            AriUtils.swap(arr, s1, provid);
            return s1;
        }
    }

    // shell 排序
    public static class Shell extends Sort04 {

        @Override
        public void sort(int[] arr) {
            for (int i = arr.length / 2; i >= 1; i /= 2) {
                int j = i;

                while (j < arr.length) {
                    int temp = arr[j];
                    int n = j - i;
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

    // 堆排序
    public static class Heap extends Sort04 {

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
                AriUtils.swap(arr, root, i);
                heapFly(arr, length, root);
            }
        }

    }

    // 桶排序
    public static class Bucket extends Sort04 {

        @Override
        public void sort(int[] arr) {
            int max = arr[0];
            int min = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) max = arr[i];
                if (arr[i] < min) min = arr[i];
            }

            int size = (max - min) / arr.length + 1;
            int cnt = (max - min) / size + 1;

            List<Integer>[] lists = new ArrayList[cnt];
            for (int i = 0; i < lists.length; i++) {
                lists[i] = new ArrayList<>();
            }
            // 入桶
            for (int i = 0; i < arr.length; i++) {
                int idx = (arr[i] - min) / size;
                lists[idx].add(arr[i]);
            }

            // 桶内元素排序
            for (int i = 0; i < lists.length; i++) {
                lists[i].sort(null);
            }

            int idx = 0;
            for (int i = 0; i < lists.length; i++) {
                for (int i1 = 0; i1 < lists[i].size(); i1++) {
                    arr[idx++] = lists[i].get(i1);
                }
            }
        }
    }

    // 计数排序
    public static class Count extends Sort04 {
        @Override
        public void sort(int[] arr) {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) max = arr[i];
            }

            int[] temp = new int[max + 1];
            for (int i = 0; i < arr.length; i++) {
                temp[arr[i]] += 1;
            }

            for (int i = 1; i < temp.length; i++) {
                temp[i] += temp[i - 1];
            }

            int[] t = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                int idx = temp[arr[i]] - 1;
                t[idx] = arr[i];
                temp[arr[i]] -= 1;
            }

            for (int i = 0; i < t.length; i++) {
                arr[i] = t[i];
            }

        }
    }

    // 基数排序
    public static class Cardinal extends Sort04 {

        @Override
        public void sort(int[] arr) {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }

            List<Integer>[] l = new ArrayList[10];
            for (int i = 0; i < l.length; i++) {
                l[i] = new ArrayList();
            }

            int cnt = (max + "").length();
            int j = 0;

            while (j <= cnt) {
                // 入桶
                for (int i = 0; i < arr.length; i++) {
                    int idx = (int) (arr[i] % Math.pow(10, j) / 10);
                    l[idx].add(arr[i]);
                }
                int d = 0;
                // 出桶
                for (int i = 0; i < l.length; i++) {
                    for (int i1 = 0; i1 < l[i].size(); i1++) {
                        arr[d++] = l[i].get(i1);
                    }
                    l[i].clear();
                }
                j++;
            }

        }
    }


    public static void main(String[] args) {
        sort(new Bubble());
        sort(new Insert());
        sort(new Select());
        sort(new Merger());
        sort(new Fast());
        sort(new Shell());
        sort(new Heap());
        sort(new Bucket());
        sort(new Count());
        sort(new Cardinal());
    }
}
