package com.tree;

import java.util.LinkedList;

/**
 * @author haizhuangbu
 * @date 2022/8/25 15:56
 * @mark BinarySearchTree 二叉搜索树
 */
public class BinarySearchTree<E> {

    private Node root;

    private int size;

    public void add(E ele) {
        // 根节点赋值
        if (root == null) {
            root = new Node(ele, null);
            size++;
            return;
        }
        int i = 0;
        Node node = root;
        Node parentNode = root;
        // 左节点赋值
        while (node != null) {
            // 为父节点赋值
            parentNode = node;
            i = compare(ele, node.ele);
            if (i < 0) {
                node = node.left;
                // 右节点赋值
            } else if (i > 0) {
                node = node.right;
            } else {
                return;
            }
        }
        if (i < 0) {
            parentNode.left = new Node(ele, parentNode);
        } else {
            parentNode.right = new Node(ele, parentNode);
        }
        size++;
    }

    private Integer compare(E ele, Object ele2) {


        return (Integer) ele - (Integer) ele2;
    }

    public void forEach() {
        if (root == null) return;
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            Node poll = nodes.poll();
            System.out.println(poll.ele);
            if (poll.left != null) {
                nodes.offer(poll.left);
            }
            if (poll.right != null) {
                nodes.offer(poll.right);
            }

        }


    }

    public static class Node<E> {

        E ele;
        Node left;
        Node right;
        Node parent;

        /**
         * @param ele    element
         * @param parent parentNode
         */
        public Node(E ele, Node parent) {
            this.ele = ele;
            this.parent = parent;
        }
    }


}
