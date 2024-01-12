package ma.prim;

import ma.Graph;
import ma.dijstra.Dijkstra;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2023/9/10 16:36
 * @mark Prim01 最小生成树算法
 */
public class Prim01 {

    private Graph graph;

    private int[] low;

    private boolean[] visited;

    private int[] path;

    public Prim01(Graph graph) {
        this.graph = graph;
        int size = graph.getEdges().length;
        visited = new boolean[size];
        path = new int[size];
        low = new int[size];
        Arrays.fill(low, Integer.MAX_VALUE);
    }

    public void prim(int start) {

        int[][] edges = graph.getEdges();
        int[] edge = edges[start];
        low[start] = 0;
        for (int i = 0; i < edge.length; i++) {
            int lowNode = findLow();
            visited[lowNode] = true;
            for (int j = 0; j < edges[lowNode].length; j++) {
                if (edges[lowNode][j] < low[j] && !visited[j] && edges[lowNode][j] != 0) {
                    low[j] = edges[lowNode][j];
                    path[j] = lowNode;
                }
            }

        }


    }

    private int findLow() {
        int minValue = Integer.MAX_VALUE;
        int min = -1;
        for (int i = 0; i < low.length; i++) {

            if (low[i] < minValue && !visited[i]) {
                minValue = low[i];
                min = i;
            }
        }


        return min;
    }

    public static void main(String[] args) {

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.initGraph();
        Graph graph1 = dijkstra.graph;
        Prim01 prim01 = new Prim01(graph1);
        prim01.prim(0);

        for (int i = 0; i < prim01.path.length; i++) {
            System.out.println(" " + i + ": " + prim01.path[i]);
        }


    }

}
