package ms;

import util.AriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/5/30 08:56
 * @mark OTone 十大经典排序算法总汇
 */
public abstract class OTone {

    public abstract String getName();

    public abstract void sort(int[] arr);

    // 冒泡
    public static class Bubble extends OTone {
        @Override
        public String getName() {
            return "冒泡排序";
        }

        @Override
        public void sort(int[] arr) {
            /**
             * 将相邻两个元素元素比较、交换两个相邻元素位置、每次比较都是用前置队列的最大值【最小值】与后续元素比较
             * 每整一遍 都会筛选出挣个队列中
             */
            for (int i = 0; i < arr.length; i++) {

                for (int j = 0; j + 1 < arr.length - i; j++) {
                    if (arr[j] > arr[j + 1]) AriUtils.swap(arr, j, j + 1);
                }
            }


        }
    }


    // 插入
    public static class Insert extends OTone {

        @Override
        public String getName() {
            return "插入排序";
        }

        @Override
        public void sort(int[] arr) {
            for (int i = 1; i < arr.length; i++) {
                int temp = arr[i];
                int j = i - 1;
                for (; j >= 0 && arr[j] > temp; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[j + 1] = temp;
            }
        }
    }

    // 选择
    public static class Select extends OTone {

        @Override
        public String getName() {
            return "选择排序";
        }

        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int j = i + 1;
                int min = i;
                while (j < arr.length) {
                    if (arr[j] < arr[min])
                        min = j;
                    j++;
                }
                AriUtils.swap(arr, i, min);

            }
        }
    }

    // 快速排序
    public static class Fast extends OTone {

        @Override
        public String getName() {
            return "快速排序";
        }

        @Override
        public void sort(int[] arr) {

            iterator(arr, 0, arr.length - 1);

        }

        /**
         * @param arr
         * @param left
         * @param right
         */
        private void iterator(int[] arr, int left, int right) {
            if (left >= right) return;
            int base = findBase(arr, left, right);
            iterator(arr, left, base - 1);
            iterator(arr, base + 1, right);
        }

        private int findBase(int[] arr, int left, int right) {
            int mid = right;
            int s1 = left;
            for (int i = left; i < arr.length; i++) {
                if (arr[i] < arr[mid]) AriUtils.swap(arr, i, s1++);
            }
            AriUtils.swap(arr, s1, mid);
            return s1;
        }
    }

    // 堆排序
    public static class Heap extends OTone {

        @Override
        public String getName() {
            return "堆排序";
        }

        @Override
        public void sort(int[] arr) {
            for (int i = arr.length / 2 - 1; i >= 0; i--) {
                heapFly(arr, arr.length, i);
            }

            for (int i = arr.length - 1; i > 0; i--) {
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
    public static class Bucket extends OTone {

        @Override
        public String getName() {
            return "桶排序";
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
            int x = 0;
            for (int i = 0; i < buckets.length; i++) {
                for (int j = 0; j < buckets[i].size(); j++) {
                    arr[x++] = buckets[i].get(j);
                }

            }


        }
    }

    // 计数排序
    public static class Count extends OTone {

        @Override
        public String getName() {
            return "计数排序";
        }

        @Override
        public void sort(int[] arr) {

            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }

            int[] buckets = new int[max + 1];
            // 计数数组
            for (int i = 0; i < arr.length; i++) {
                buckets[arr[i]] += 1;
            }

            // 累计数组
            for (int i = 1; i < buckets.length; i++) {
                buckets[i] += buckets[i - 1];
            }
            int[] temp = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                int idx = buckets[arr[i]] - 1;
                temp[idx] = arr[i];
                buckets[arr[i]] -= 1;
            }
            for (int i = 0; i < temp.length; i++) {
                arr[i] = temp[i];
            }

        }
    }

    // 希尔排序
    public static class Shell extends OTone {

        @Override
        public String getName() {
            return "希尔排序";
        }

        @Override
        public void sort(int[] arr) {
            // 减小增量排序
            for (int i = arr.length / 2; i > 0; i--) {

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

    // 基数排序
    public static class Cardinal extends OTone {
        @Override
        public String getName() {
            return "基数排序";
        }

        @Override
        public void sort(int[] arr) {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }
            int cnt = max + "".length();

            int j = 0;
            List<Integer>[] buckets = new ArrayList[10];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new ArrayList<>();
            }
            while (j <= cnt) {
                // 入桶
                for (int i = 0; i < arr.length; i++) {
                    int base = (int) (arr[i] / Math.pow(10, j) % 10);
                    buckets[base].add(arr[i]);
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

    // 归并排序
    public static class Merger extends OTone {

        @Override
        public String getName() {
            return "归并排序";
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
            int i = 0;
            int[] temp = new int[right - left + 1];

            while (s1 <= mid && s2 <= right) {
                if (arr[s1] < arr[s2]) temp[i++] = arr[s1++];
                else temp[i++] = arr[s2++];
            }

            while (s1 <= mid) {
                temp[i++] = arr[s1++];
            }

            while (s2 <= right) {
                temp[i++] = arr[s2++];
            }
            for (int j = 0; j < temp.length; j++) {
                arr[left + j] = temp[j];


            }

        }
    }


    public static void sort(OTone oTone) {
        int[] arr = AriUtils.randomArr(10);
        oTone.sort(arr);
        System.out.println(oTone.getName() + "----------------------");
        AriUtils.print(arr);

    }

    public static void main(String[] args) {
        sort(new Bubble());
        sort(new Insert());
        sort(new Select());
        sort(new Fast());
        sort(new Heap());
        sort(new Bucket());
        sort(new Count());
        sort(new Shell());
        sort(new Cardinal());
        sort(new Merger());


    }
}
