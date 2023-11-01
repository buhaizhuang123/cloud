package ma.prim;

import ma.Graph;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2023/9/7 08:38
 * @mark Prim 普里姆最小生成树算法
 */
public class Prim {

    private Graph graph;

    private boolean[] visited;

    private int[] low;
    private int[] path;


    public Prim(int max) {
        this.graph = new Graph(max);
        visited = new boolean[max];
        low = new int[max];
        path = new int[max];
        Arrays.fill(low, Integer.MAX_VALUE);
        initGroup();
    }

    public void initGroup() {

        graph.addEdge(0, 0, 0);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(0, 4, 6);
        graph.addEdge(1, 1, 0);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 4, 5);
        graph.addEdge(4, 3, 9);
        graph.addEdge(4, 4, 0);
    }

    public void prim(int v) {

        low[v] = 0;
        int[][] edges = graph.getEdges();
        for (int i = 0; i < edges.length - 1; i++) {
            int minNode = findMin(low);
            visited[minNode] = true;
            for (int j = 0; j < edges[minNode].length; j++) {
                if (low[j] > edges[minNode][j] && edges[minNode][j] != 0 && !visited[j]) {
                    low[j] = edges[minNode][j];
                    path[j] = minNode;
                }
            }
        }

        Arrays.stream(path).forEach(System.out::print);

    }

    private int findMin(int[] low) {
        int minNode = -1;
        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < low.length; i++) {

            if (low[i] < minValue && !visited[i]) {
                minValue = low[i];
                minNode = i;
            }

        }
        return minNode;
    }


    public static void main(String[] args) {
        Prim prim = new Prim(5);
        prim.prim(0);
    }

}
