package coll.tree;

import coll.tree.po.Node;

import java.util.*;

/**
 * @author haizhuangbu
 * @date 2023/9/1 10:54
 * @mark Dfs 广度优先算法
 */
public class Bfs {

    private List<Node> visited = new ArrayList<>();


    public void bfs(Node start) {


        Queue<Node> queue = new ArrayDeque<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.getName());
            List<Node> linkList = poll.getLinkList();
            linkList.stream().filter(i -> !visited.contains(i))
                    .forEach(queue::add);
        }


    }


}
