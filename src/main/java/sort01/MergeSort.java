package sort01;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2024/1/22 16:02
 * @mark MergeSort 归并排序
 */
public class MergeSort {

    public void sort() {

        int[] arr = AriUtils.randomArr(10);
        AriUtils.print(arr);
        sort(arr, 0, arr.length - 1);
        AriUtils.print(arr);


    }

    private void sort(int[] arr, int left, int right) {

        if (left >= right) return;
        // 中间值
        int mid = (left + right) / 2;
        // 左循环
        sort(arr, left, mid);
        // 有循环
        sort(arr, mid + 1, right);
        // 归并元素
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        // 左节点
        int s1 = left;
        // 右节点
        int s2 = mid + 1;
        // 临时数组 存放比较后的有序集合
        int[] tempArr = new int[right - left + 1];
        int temp = 0;

        while (s1 <= mid && s2 <= right) {
            if (arr[s1] < arr[s2]) {
                tempArr[temp++] = arr[s1++];
            } else {
                tempArr[temp++] = arr[s2++];
            }
        }

        while (s1 <= mid) {
            tempArr[temp++] = arr[s1++];
        }
        while (s2 <= right) {
            tempArr[temp++] = arr[s2++];
        }

        // 将有序元素放入原始数组
        for (int i = 0; i < tempArr.length; i++) {
            arr[left + i] = tempArr[i];
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort();
    }


}
