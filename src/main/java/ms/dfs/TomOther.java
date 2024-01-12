package ms.dfs;

import util.AriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/9/7 20:00
 * @mark TomOther
 */
abstract public class TomOther {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract public void sort(int[] arr);

    // 冒泡排序
    public static class Bubble extends TomOther {
        @Override
        public String getName() {
            return "冒泡排序";
        }

        @Override
        public void sort(int[] arr) {

            for (int i = 0; i < arr.length; i++) {
                int j = 0;
                while (j + 1 < arr.length - i) {
                    if (arr[j] > arr[j + 1]) {
                        AriUtils.swap(arr, j, j + 1);
                    }
                    j++;
                }
            }


        }
    }


    // 选择排序
    public static class Select extends TomOther {
        @Override
        public String getName() {
            return "选择排序";
        }

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

    // 插入排序
    public static class Insert extends TomOther {
        @Override
        public String getName() {
            return "插入排序";
        }

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

    // 归并排序
    public static class Merge extends TomOther {

        @Override
        public String getName() {
            return "归并排序";
        }

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
            merge(arr, left, mid, right);
        }

        private void merge(int[] arr, int left, int mid, int right) {

            int s1 = left;
            int s2 = mid + 1;
            int[] temp = new int[right - left + 1];
            int idx = 0;
            while (s1 <= mid && s2 <= right) {
                if (arr[s1] < arr[s2]) {
                    temp[idx++] = arr[s1++];
                } else {
                    temp[idx++] = arr[s2++];
                }
            }
            while (s1 <= mid) {
                temp[idx++] = arr[s1++];
            }

            while (s2 <= right) {
                temp[idx++] = arr[s2++];
            }

            for (int i = 0; i < temp.length; i++) {
                arr[left + i] = temp[i];
            }


        }
    }

    // 堆排序
    public static class Heap extends TomOther {
        @Override
        public String getName() {
            return "堆排序";
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
            if (left < length && arr[root] < arr[left]) {
                root = left;
            }

            if (right < length && arr[root] < arr[right]) {
                root = right;
            }

            if (root != i) {
                AriUtils.swap(arr, root, i);
                heapFly(arr, length, root);
            }

        }
    }

    // 桶排序
    public static class Bucket extends TomOther {

        @Override
        public void sort(int[] arr) {
            int max = arr[0];
            int min = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                }

                if (arr[i] < min) min = arr[i];
            }

            int size = (max + min) / arr.length + 1;
            int cnt = (max + min) / size + 1;

            List<Integer>[] buckets = new ArrayList[cnt];
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
            // 出桶
            int t = 0;
            for (int i = 0; i < buckets.length; i++) {
                for (int i1 = 0; i1 < buckets[i].size(); i1++) {
                    arr[t++] = buckets[i].get(i1);
                }
            }

        }
    }

    // 计数排序
    public static class Count extends TomOther {

        @Override
        public String getName() {
            return "计数排序";
        }

        @Override
        public void sort(int[] arr) {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) max = arr[i];
            }

            int[] bucket = new int[max + 1];
            for (int i = 0; i < arr.length; i++) {
                bucket[arr[i]] = 1;
            }

            for (int i = 1; i < bucket.length; i++) {
                bucket[i] += bucket[i - 1];
            }
            int[] temp = new int[arr.length];

            for (int i = 0; i < arr.length; i++) {
                int idx = bucket[arr[i]] - 1;
                temp[idx] = arr[i];
                bucket[arr[i]]--;
            }

            for (int i = 0; i < temp.length; i++) {
                arr[i] = temp[i];
            }


        }
    }

    // 基数排序
    public static class Cardinal extends TomOther {

        @Override
        public void sort(int[] arr) {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }
            // 位数
            int cnt = (max + "").length();
            ArrayList<Integer>[] arrayLists = new ArrayList[10];
            for (int i = 0; i < arrayLists.length; i++) {
                arrayLists[i] = new ArrayList<>();
            }
            int j = 0;
            while (j <= cnt) {
                // save
                for (int i = 0; i < arr.length; i++) {
                    int idx = (int) (arr[i] / Math.pow(10, j) % 10);
                    arrayLists[idx].add(arr[i]);
                }

                int z = 0;
                // out
                for (int i = 0; i < arrayLists.length; i++) {

                    for (int i1 = 0; i1 < arrayLists[i].size(); i1++) {
                        arr[z++] = arrayLists[i].get(i1);
                    }
                    arrayLists[i].clear();
                }


                j++;
            }

        }
    }

    // shell 希尔排序
    public static class Shell extends TomOther {

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

    // 快速排序
    public static class Fast extends TomOther {

        @Override
        public void sort(int[] arr) {
            sort(arr, 0, arr.length - 1);


        }

        private void sort(int[] arr, int left, int right) {
            if (left >= right) return;
            int mid = findMid(arr, left, right);
            sort(arr, left, mid - 1);
            sort(arr, mid + 1, right);


        }

        private int findMid(int[] arr, int left, int right) {
            int provid = right;
            int s1 = left;
            for (int s2 = left; s2 < provid; s2++) {
                if (arr[s2] <= arr[provid]) AriUtils.swap(arr, s1++, s2);
            }
            AriUtils.swap(arr, s1, provid);
            return s1;
        }
    }

    public static void sort(TomOther tomOther) {
        int[] arr = AriUtils.randomArr(10);
        tomOther.sort(arr);
        System.out.println(tomOther.getName());
        AriUtils.print(arr);
    }

    public static void main(String[] args) {

        sort(new Bubble());
        sort(new Select());
        sort(new Insert());
        sort(new Merge());
        sort(new Heap());
        sort(new Bucket());
        sort(new Count());
        sort(new Cardinal());
        sort(new Shell());
        sort(new Fast());

    }


}
