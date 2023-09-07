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

        int[][] edges = graph.getEdges();
        low[v] = 0;
        for (int i = 0; i < edges.length - 1; i++) {
            int minNode = findMinNode(low);
            visited[minNode] = true;
            for (int j = 0; j < edges[minNode].length; j++) {
                if (edges[minNode][j] !=0 && low[j] > edges[minNode][j] && !visited[j]) {
                    low[j] = edges[minNode][j];
                    path[j] = minNode;
                }
            }
        }


        Arrays.stream(path).forEach(System.out::print);
    }

    public int findMinNode(int[] low) {
        int min = Integer.MAX_VALUE;
        int minV = -1;
        for (int i = 0; i < low.length; i++) {
            if (low[i] < min && !visited[i]) {
                min = low[i];
                minV = i;
            }
        }
        return minV;
    }


    public static void main(String[] args) {
        Prim prim = new Prim(5);
        prim.prim(0);
    }

}
