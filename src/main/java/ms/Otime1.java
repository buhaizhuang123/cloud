package ms;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2023/5/30 17:11
 * @mark Otime1
 */
public abstract class Otime1 {

    public static class Bubble extends Otime1 {


        @Override
        public void sort(int[] arr) {
            // 比较次数 n
            for (int i = 0; i < arr.length; i++) {
                int j = 0;
                // 比较相邻元素位置
                while (j + 1 < arr.length - i) {
                    if (arr[j] > arr[j + 1]) {
                        AriUtils.swap(arr, j + 1, j);
                    }
                    j++;
                }
            }
        }
    }

    public static class Insert extends Otime1 {

        @Override
        public void sort(int[] arr) {
            for (int i = 1; i < arr.length; i++) {
                // 前置元素j
                int j = i - 1;
                // 当前元素
                int temp = arr[i];
                // 不符合规则向后移动
                while (j >= 0 && arr[j] > temp) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                // 将元素插入对应位置
                arr[j + 1] = temp;
            }
        }
    }

    // 选择
    public static class Select extends Otime1 {
        @Override
        public void sort(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                int j = i + 1;
                int min = i;
                while (j < arr.length) {
                    if (arr[min] > arr[j]) min = j;
                    j++;
                }
                AriUtils.swap(arr, min, i);
            }

        }
    }

    // 快速排序
    public static class Fast extends Otime1 {

        @Override
        public void sort(int[] arr) {

            iterator(arr, 0, arr.length - 1);

        }

        private void iterator(int[] arr, int left, int right) {
            if (left >= right) return;
            int base = findMid(arr, left, right);
            iterator(arr, left, base - 1);
            iterator(arr, base + 1, right);

        }

        private int findMid(int[] arr, int left, int right) {
            int provid = right;
            int s1 = left;

            for (int i = left; i < arr.length; i++) {
                if (arr[i] < arr[provid]) AriUtils.swap(arr, i, s1++);
            }
            AriUtils.swap(arr, provid, s1);
            return s1;
        }
    }

    // 堆排序
    public static class Heap extends Otime1 {


        @Override
        public void sort(int[] arr) {

            for (int i = arr.length / 2 - 1; i >= 0; i--) {
                heapFly(arr, arr.length, i);
            }


            for (int i = arr.length - 1; i >= 0; i--) {

            }

        }

        private void heapFly(int[] arr, int length, int i) {

            int left = i * 2 + 1;
            int right = i * 2 + 2;
            int root = i;
            if (left < length && arr[root] < arr[left]) root = left;

            if (left < length && arr[root] < arr[right]) root = right;

            if (root != i) {
                AriUtils.swap(arr, root, i);
                heapFly(arr, length, root);
            }

        }
    }

    // 计数排序
    public static class Count extends Otime1 {

        @Override
        public void sort(int[] arr) {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (max < arr[i]) max = arr[i];
            }

            int[] counter = new int[max + 1];
            // 计数
            for (int i = 0; i < arr.length; i++) {
                counter[arr[i]] += 1;
            }

            // 累计
            for (int i = 1; i < counter.length; i++) {
                counter[i] += counter[i - 1];
            }


            int[] temp = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                int idx = counter[arr[i]] - 1;
                temp[idx] = arr[i];
                counter[arr[i]] -= 1;
            }

            for (int i = 0; i < temp.length; i++) {

                arr[i] = temp[i];

            }


        }
    }

    // 归并排序
    public static class Merger extends Otime1 {

        @Override
        public void sort(int[] arr) {

            iterator(arr, 0, arr.length - 1);

        }

        private void iterator(int[] arr, int left, int right) {
            if (left >= right) {
                return;
            }
            int mid = (left + right) / 2;
            iterator(arr, left, mid);
            iterator(arr, mid + 1, right);
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

            for (int j = 0; j < temp.length; j++) {

                arr[left + j] = temp[i];

            }


        }
    }

    // 基数排序

    // 桶排序

    // 希尔排序


    public abstract void sort(int[] arr);

    public static void sort(Otime1 otime1) {
        int[] arr = AriUtils.randomArr(10);
        otime1.sort(arr);
        AriUtils.print(arr);
    }

    public static void main(String[] args) {
        sort(new Bubble());
        sort(new Insert());
        sort(new Select());
        sort(new Fast());
        sort(new Count());
    }

}
