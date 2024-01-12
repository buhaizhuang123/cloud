package ari;

import ma.Graph;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2023/10/9 16:18
 * @mark Dijkstra 迪杰斯特拉算法
 */
public class Dijkstra {

    private Graph graph;
    private boolean[] visited;

    private int[] low;

    /**
     * @param v 顶点数量
     */
    public Dijkstra(int v) {

        graph = new Graph(v);
        graph.addEdge(0, 0, 0);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 5);
        graph.addEdge(0, 6, 5);
        graph.addEdge(1, 2, 3);
        graph.addEdge(3, 2, 3);
        graph.addEdge(1, 4, 2);
        graph.addEdge(1, 5, 6);
        graph.addEdge(2, 3, 6);
        graph.addEdge(2, 5, 1);

        visited = new boolean[v];

        Arrays.fill(visited, false);

        low = new int[v];

        Arrays.fill(low, 0);

    }


    public void mk(int start) {

        int[][] edges = graph.getEdges();
        int[] edge = edges[start];
        visited[start] = true;
        int min = -1;
        for (int i = 0; i < edge.length; i++) {
            min = findMin();
            if (edges[start][i] != 0 && !visited[i]
                    && (low[i] > low[start] + edges[start][i] || low[i] == 0)) {
                low[i] = low[start] + edges[start][i];
            }
        }

        if (min != -1) mk(min);


    }

    private int findMin() {
        int minVal = Integer.MAX_VALUE;
        int min = -1;
        for (int i = 0; i < low.length; i++) {

            int val = low[i];
            if (minVal > val && val != 0 && !visited[i]) {
                minVal = val;
                min = i;
            }

        }
        return min;
    }


    public void foreach() {
        Arrays.stream(low).forEach(System.out::print);

    }

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra(7);
        dijkstra.mk(0);
        dijkstra.foreach();

    }


}
