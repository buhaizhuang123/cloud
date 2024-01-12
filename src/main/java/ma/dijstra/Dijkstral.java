package ma.dijstra;

import ma.Graph;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2023/9/10 15:37
 * @mark Dijkstral
 */
public class Dijkstral {


    private Graph graph;

    private int[] low;

    private boolean[] visited;

    public Dijkstral(Graph graph) {
        this.graph = graph;
        int[][] edges = graph.getEdges();
        low = new int[edges.length];
        visited = new boolean[edges.length];
        Arrays.fill(low, Integer.MAX_VALUE);
    }

    public void dijkstra(int start) {
        // 设置初始节点为0
        low[start] = 0;

        int[][] edges = graph.getEdges();
        // 遍历起始节点下的所有节点
        for (int i = 0; i < edges.length - 1; i++) {
            int lowNode = findLow();
            visited[lowNode] = true;
            for (int j = 0; j < edges[lowNode].length; j++) {
                if (low[j] > low[lowNode] + edges[lowNode][j] && !visited[j] && edges[lowNode][j] != 0) {
                    low[j] = low[lowNode] + edges[lowNode][j];
                }
            }
        }
    }

    private int findLow() {
        int min = -1;
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < low.length; i++) {
            if (low[i] < minVal && !visited[i]) {
                min = i;
                minVal = low[i];
            }
        }
        return min;
    }


    public static void main(String[] args) {

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.initGraph();
        Graph graph1 = dijkstra.graph;

        Dijkstral dijkstral = new Dijkstral(graph1);
        dijkstral.dijkstra(0);
        Arrays.stream(dijkstral.low).forEach(i -> System.out.print(" " + i));


    }

}
