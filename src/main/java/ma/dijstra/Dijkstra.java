package ma.dijstra;

import ma.Graph;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2023/9/8 15:06
 * @mark Dijkstra 迪杰斯特拉
 */
public class Dijkstra {

    public Graph graph = new Graph(7);

    public int[] low = new int[7];

    private boolean[] visited = new boolean[7];

    public void initGraph() {
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 3, 15);
        graph.addEdge(0, 5, 17);
        graph.addEdge(1, 2, 6);
        graph.addEdge(1, 4, 2);
        graph.addEdge(1, 6, 6);
        graph.addEdge(2, 3, 3);
        graph.addEdge(4, 3, 4);
        graph.addEdge(6, 5, 4);
        Arrays.fill(low, Integer.MAX_VALUE);
    }

    public void dijkstra(int start) {
        visited[start] = true;
        int[][] edges = graph.getEdges();
        int[] edge = edges[start];
        int min = -1;
        for (int i = 0; i < edge.length; i++) {
            min = findMin();
            if (edges[start][i] != 0 && !visited[i] && low[i] > low[start] + edges[start][i]) {
                low[i] = low[start] + edges[start][i];
            }
        }
        if (min != -1)
            dijkstra(min);
    }

    private int findMin() {

        int minValue = Integer.MAX_VALUE;
        int min = -1;
        for (int i = 0; i < low.length; i++) {
            if (!visited[i] && minValue > low[i]) {
                minValue = low[i];
                min = i;
            }

        }
        return min;
    }

    public void dijkstra() {
        low[0] = 0;
        dijkstra(0);
    }


    public static void main(String[] args) {
        Dijkstra dijstra = new Dijkstra();
        dijstra.initGraph();
        dijstra.dijkstra();
        for (int i = 0; i < dijstra.low.length; i++) {
            System.out.print(" " + dijstra.low[i]);
        }
    }

}
