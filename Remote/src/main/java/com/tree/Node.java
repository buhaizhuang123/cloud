package com.tree;

import java.util.HashMap;

/**
 * @author haizhuangbu
 * @date 2022/8/25 10:25
 * @mark Node 节点具备的增删改查方法
 */
public class Node {

    private Integer value;

    // 左叶子结点
    private Node left;

    // 右叶子结点
    private Node right;

    // 根节点
    private Node root;

    private StringBuilder l;

    private StringBuilder r;

    public Boolean insert(Node node) {
        if (root == null) {
            this.root = node;
            return true;
        }

        if (root.value <= node.value) {
            l.append(" ");
            if (root.left == null) {
                node.root = this;
                root.left = node;
                return true;
            } else {
                root.left.insert(node);
            }

        }

        if (root.value > node.value) {
            r.append(" ");
            if (root.right == null) {
                node.root = this;
                root.right = node;
                return true;
            } else {
                root.right.insert(node);
            }
        }
        return false;
    }

    public void sya() {

        if (this.left != null) {
            System.out.println(l.toString() + this.left.value);
        }


        if (this.right != null) {
            System.out.println(r.toString() + this.right.value);
        }
    }


    public void forEach() {
        if (this.left != null) {
            sya();
        }

        if (this.right != null) {
            sya();
        }

    }


}
