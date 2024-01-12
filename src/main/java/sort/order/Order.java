package sort.order;

import util.AriUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/6/18 08:44
 * @mark Order
 */
abstract public class Order {


    public abstract void sort(int[] arr);


    // 冒泡排序
    public static class Bubble extends Order {
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

        @Override
        public String getName() {
            return "Bubble";
        }
    }

    // 插入排序
    static class Insert extends Order {
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

        @Override
        public String getName() {
            return "insert";
        }
    }

    // 选择排序
    static class Select extends Order {
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

                AriUtils.swap(arr, i, min);
            }


        }

        @Override
        public String getName() {
            return "select";
        }
    }

    // 快速排序
    static class Fast extends Order {
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
            int p = right;

            int s1 = left;
            for (int s2 = left; s2 < right; s2++) {
                if (arr[s2] < arr[p]) AriUtils.swap(arr, s1++, s2);
            }
            AriUtils.swap(arr, s1, p);
            return s1;
        }

        @Override
        public String getName() {
            return "fast";
        }
    }

    // 堆排序
    static class Heap extends Order {
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
            if (left < n && arr[root] < arr[left]) root = left;
            if (right < n && arr[root] < arr[right]) root = right;
            if (root != i) {
                AriUtils.swap(arr, root, i);
                heapFly(arr, n, root);
            }

        }

        @Override
        public String getName() {
            return "heap";
        }
    }

    // 计数排序
    static class Count extends Order {
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

        @Override
        public String getName() {
            return "count";
        }
    }

    // 基数排序
    static class Cardinal extends Order {
        @Override
        public void sort(int[] arr) {
            // 初始化桶
            List<Integer>[] bucket = new ArrayList[10];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList();
            }

            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }
            int j = (max + "").length();

            int cnt = 0;

            while (cnt <= j) {
                // 入桶
                for (int i = 0; i < arr.length; i++) {
                    int z = (int) (arr[i] / Math.pow(10, cnt) % 10);
                    bucket[z].add(arr[i]);
                }
                int temp = 0;
                // 出桶
                for (int i = 0; i < bucket.length; i++) {
                    for (int z = 0; z < bucket[i].size(); z++) {
                        arr[temp++] = bucket[i].get(z);
                    }
                    bucket[i].clear();
                }


                cnt++;
            }


        }

        @Override
        public String getName() {
            return "cardinal";
        }
    }

    // shell排序
    static class Shell extends Order {
        @Override
        public void sort(int[] arr) {
            // 降低增量排序
            for (int i = arr.length / 2; i >= 1; i /= 2) {
                for (int z = i; z < arr.length; z++) {
                    int x = z - i;
                    int temp = arr[z];
                    while (x >= 0 && arr[x] > temp) {
                        arr[x + i] = arr[x];
                        x -= i;
                    }
                    arr[x + i] = temp;
                }
            }


        }

        @Override
        public String getName() {
            return "shell";
        }
    }

    // 归并排序
    static class Merger extends Order {
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
            int idx = 0;
            while (s1 <= mid && s2 <= right) {
                if (arr[s1] < arr[s2]) temp[idx++] = arr[s1++];
                else temp[idx++] = arr[s2++];
            }

            while (s1 <= mid) temp[idx++] = arr[s1++];
            while (s2 <= right) temp[idx++] = arr[s2++];

            for (int i = 0; i < temp.length; i++) {
                arr[left + i] = temp[i];
            }


        }

        @Override
        public String getName() {
            return "merger";
        }
    }

    // 桶排序
    static class Bucket extends Order {
        @Override
        public void sort(int[] arr) {

            int min = arr[0];
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (min > arr[i]) min = arr[i];
                if (max < arr[i]) max = arr[i];
            }

            int size = (max - min) / arr.length + 1;
            int cnt = (max - min) / size + 1;
            // 初始化桶
            List<Integer>[] bucket = new ArrayList[cnt];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList<>();
            }

            // 元素入桶
            for (int i = 0; i < arr.length; i++) {
                int idx = (arr[i] - min) / size;
                bucket[idx].add(arr[i]);
            }

            // 桶内元素排序
            for (int i = 0; i < bucket.length; i++) {
                bucket[i].sort(null);
            }
            int temp = 0;
            // 将桶内元素取出
            for (int i = 0; i < bucket.length; i++) {
                for (Integer value : bucket[i]) {
                    arr[temp++] = value;
                }
            }


        }

        @Override
        public String getName() {
            return "bucket";
        }
    }

    public static void sort(Order order) {

        int[] arr = AriUtils.randomArr(10);
        System.out.println(order.getName());
        order.sort(arr);
        AriUtils.print(arr);


    }

    public abstract String getName();


    public static void main(String[] args) {

        Order.sort(new Bubble());
        Order.sort(new Insert());
        Order.sort(new Select());
        Order.sort(new Fast());
        Order.sort(new Heap());
        Order.sort(new Count());
        Order.sort(new Cardinal());
        Order.sort(new Shell());
        Order.sort(new Merger());
        Order.sort(new Bucket());


    }


}
