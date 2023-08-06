package cons.tree;


import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author haizhuangbu
 * @date 2023/7/16 07:53
 * @mark Bst
 */
public class Bst<Key extends Comparable<Key>, Value> {


    private Node root;

    private class Node {
        private Key key;

        private Value value;

        private Node left, right;

        private int n;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }


    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if (root == null) return 0;
        return root.n;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) return get(root.left, key);
        else if (cmp > 0) return get(root.right, key);
        else return root.value;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node root, Key key, Value value) {
        if (root == null) root = new Node(key, value, 1);
        int cmp = key.compareTo(root.key);
        if (cmp < 0) put(root.left, key, value);
        else if (cmp > 0) put(root.right, key, value);
        else root.value = value;
        root.n = size(root.left) + size(root.right) + 1;
        return root;
    }

    private Key min() {
        return min(root).key;
    }

    private Node min(Node root) {
        if (root == null) {
            return null;
        }
        return min(root.left);
    }


    private Key max() {
        return max(root).key;
    }

    private Node max(Node root) {
        if (root == null) {
            return null;
        }
        return min(root.right);
    }


    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) return null;
        return node.key;
    }

    private Node floor(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) return floor(root.left, key);
        else if (cmp > 0) {
            Node floor = floor(root.right, key);
            if (floor != null) return floor;
            else return root;
        } else return root;
    }

    /**
     * @param k 节点
     * @return 返回排名为k的节点
     */
    private Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node root, int k) {
        if (root == null) return null;
        int size = size(root.left);
        if (size > k) return select(root.left, k);
        else if (size < k) return select(root.right, k - size - 1);
        else return root;
    }


    private int rank(Key key) {
        return rank(root, key);
    }

    /**
     * @param key
     * @param root
     * @return 返回以root 为根节点的数量
     */
    private int rank(Node root, Key key) {
        if (root == null) return 0;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) return rank(root.left, key);
        else if (cmp > 0) return 1 + size(root.left) + rank(root.right, key);
        else return size(root.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node root) {
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);
        root.n = size(root.left) + size(root.right) + 1;
        return root;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) root.left = delete(root.left, key);
        else if (cmp > 0) root.right = delete(root.right, key);
        else {
            if (root.right == null) return root.left;
            if (root.left == null) return root.right;
            Node t = root;
            root = min(t.right);
            root.right = deleteMin(t.right);
            root.left = t.left;
        }
        root.n = size(root.left) + size(root.right) + 1;
        return root;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key min, Key max) {
        Queue<Key> queue = new ArrayDeque<>();
        keys(root, queue, min, max);
        return queue;
    }

    private void keys(Node root, Queue<Key> queue, Key min, Key max) {
        if (root == null) return;
        int l = min.compareTo(root.key);
        int r = max.compareTo(root.key);
        if (l < 0) keys(root.left, queue, min, max);
        if (l <= 0 && r >= 0) queue.add(root.key);
        if (r > 0) keys(root.right, queue, min, max);
    }


}
