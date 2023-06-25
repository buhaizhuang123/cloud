package ms.dfs;

import util.AriUtils;

/**
 * @author haizhuangbu
 * @date 2023/6/5 08:51
 * @mark Node
 */
public class Node {

    private Integer vlu;

    private Node left;

    private Node right;

    public Integer getVlu() {
        return vlu;
    }

    public void setVlu(Integer vlu) {
        this.vlu = vlu;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node(Integer vlu) {
        this.vlu = vlu;
    }

    public static Node getInstance(Integer vlu) {
        return new Node(vlu);
    }

    public static Node build() {

        int[] arr = AriUtils.randomArr(10);

        Node left = null;
        Node right = null;

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            Node node = heapFly(arr, arr.length, i);
            // right
            if (i % 2 == 0) {
                right = node;
            } else {
                // left
                left = node;
            }

        }

        return null;
    }

    private static Node heapFly(int[] arr, int length, int i) {
        Node instance = getInstance(i);
        int left = i * 2 + 1;

        int right = i * 2 + 2;

        if (left < length) instance.setLeft(getInstance(left));

        if (right < length) instance.setRight(getInstance(right));


        return instance;
    }


}
