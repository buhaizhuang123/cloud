package com.tree.ts;

import com.tree.BinarySearchTree;

/**
 * @author haizhuangbu
 * @date 2022/8/25 16:23
 * @mark BinaryTreeTs
 */
public class BinaryTreeTs {

    public static void main(String[] args) {

        BinarySearchTree<Integer> binary = new BinarySearchTree<>();
        int[] arr = new int[]{5, 1, 2, 6, 7};
        for (int i : arr) {
            binary.add(i);
        }
        binary.forEach();
        System.out.println(binary);

    }

}
