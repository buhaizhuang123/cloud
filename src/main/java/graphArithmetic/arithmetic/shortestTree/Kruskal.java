package graphArithmetic.arithmetic.shortestTree;

import graphArithmetic.Graph;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2024/1/31 14:16
 * @mark Kruskal 克鲁斯卡尔算法
 */

public class Kruskal {

    private Graph graph;

    private int[] parent;

    public Kruskal(Graph graph) {
        this.graph = graph;
        parent = new int[graph.getVertex().length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }


    public void kruskal() {

        int[] vertex = graph.getVertex();

        int[][] edges = graph.getEdges();

        List<Edge> sortedEdges = new ArrayList();
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] != 0) {
                    sortedEdges.add(new Edge(i, j, edges[i][j]));
                }
            }
        }
        // 排序
        sortedEdges = sortedEdges.stream().sorted((left, right) -> left.getWeight() < right.getWeight() ? -1 : 1)
                .collect(Collectors.toList());

        // 生成树算法
        ArrayList<Edge> minimum = new ArrayList<>();
        for (int i = 0; i < vertex.length - 1; ) {
            // 取出第一个元素
            Edge edge = sortedEdges.remove(0);
            int n = find(edge.getStart());
            int m = find(edge.getEnd());
            if (n != m) {
                parent[edge.getStart()] = edge.getEnd();
                minimum.add(edge);
                i++;
            }
        }
    }

    // 判断是否满足树要求,形成环
    public int find(int n) {
        while (parent[n] != n) {
            n = parent[n];
        }
        return n;
    }


    public static void main(String[] args) {

        Graph g = new Graph(4);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 2, 4);
        g.addEdge(1, 0, 1);
        g.addEdge(2, 0, 2);
        g.addEdge(3, 1, 5);
        Kruskal kruskal = new Kruskal(g);
        kruskal.kruskal();


    }


    @AllArgsConstructor
    @Data
    public static class Edge {

        private int start;
        private int end;
        private int weight;

    }


}
