package coll.tree.po;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/9/1 10:55
 * @mark Node
 */
public class Node{

    private String name;

    private List<Node> linkList;

    public Node(String name) {
        this.name = name;
        this.linkList = new ArrayList<>();
    }

    public void add(Node... nodes) {
        linkList.addAll(Arrays.asList(nodes));
    }

    public String getName() {
        return name;
    }

    public List<Node> getLinkList() {
        return linkList;
    }
}
