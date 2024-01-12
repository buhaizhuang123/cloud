package sort;

import sort.base.*;
import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2023/6/6 09:02
 * @mark Sort
 */
public abstract class Sort {

    abstract public void sort(int[] arr);

    abstract public String getName();

    public static void sort(Sort sort) {
        int[] arr = AriUtils.randomArr(10);
        sort.sort(arr);
        System.out.print(sort.getName() + ":=====");
        AriUtils.print(arr);
    }

    public static void main(String[] args) {
        Sort.sort(new Bubble());
        Sort.sort(new Select());
        Sort.sort(new Insert());
        Sort.sort(new Merger());
        Sort.sort(new Bucket());
        Sort.sort(new Cardinal());
        Sort.sort(new Count());
        Sort.sort(new Fast());
        Sort.sort(new Heap());
        Sort.sort(new Shell());

    }

}
