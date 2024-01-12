package ari;

import ma.Graph;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2023/10/9 17:39
 * @mark Prim 普雷姆算法
 */
public class Prim {

    private Graph graph;

    private boolean[] visited;

    private int[] parent;
    private int[] low;

    public Prim(int v) {
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
        parent = new int[v];
        low = new int[v];
        Arrays.fill(low, Integer.MAX_VALUE);
    }


    public void mk(int start) {

        int[][] edges = graph.getEdges();

        int[] edge = edges[start];
        low[start] = 0;
        parent[start] = start;
        for (int i = 0; i < edge.length; i++) {
            int min = findMin();
            if (min == -1) return;
            visited[min] = true;
            for (int j = 0; j < edges[min].length; j++) {
                if (!visited[j] && low[j] > edges[min][j] &&  edges[min][j] != 0) {
                    low[j] = edges[min][j];
                    parent[j] = min;
                }
            }
        }


    }

    private int findMin() {
        int minVal = Integer.MAX_VALUE;
        int min = -1;
        for (int i = 0; i < low.length; i++) {

            int val = low[i];
            if (minVal > val  && !visited[i]) {
                minVal = val;
                min = i;
            }

        }
        return min;
    }

    public void foreach() {

        Arrays.stream(parent).forEach(System.out::print);

    }


    public static void main(String[] args) {
        Prim prim = new Prim(7);
        prim.mk(0);
        prim.foreach();
    }

}
