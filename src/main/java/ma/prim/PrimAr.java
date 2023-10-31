package ma.prim;

import ma.Graph;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2023/9/8 16:38
 * @mark PrimAr 普雷姆算法
 */
public class PrimAr {


    public Graph graph = new Graph(7);

    public int[] low = new int[7];
    public int[] path = new int[7];

    private boolean[] visited = new boolean[7];

    public void initGraph() {

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 3, 9);
        graph.addEdge(0, 5, 17);
        graph.addEdge(1, 2, 6);
        graph.addEdge(1, 4, 2);
        graph.addEdge(1, 6, 6);
        graph.addEdge(2, 3, 3);
        graph.addEdge(4, 3, 4);
        graph.addEdge(6, 5, 4);
        Arrays.fill(low, Integer.MAX_VALUE);
    }


    public void prim(int start) {

        int[][] edges = graph.getEdges();
        low[start] = 0;
        int[] edge = edges[start];
        for (int i = 0; i < edge.length; i++) {
            int minNode = findMin();
            if (minNode == -1) return;
            visited[minNode] = true;
            for (int j = 0; j < edges[minNode].length; j++) {
                if (!visited[j] && low[j] > edges[minNode][j] && edges[minNode][j] != 0) {
                    low[j] = edges[minNode][j];
                    path[j] = minNode;
                }
            }
        }
    }

    private int findMin() {
        int minVal = Integer.MAX_VALUE;
        int minNode = -1;
        for (int i = 0; i < low.length; i++) {
            if (low[i] < minVal && !visited[i]) {
                minVal = low[i];
                minNode = i;
            }
        }
        return minNode;
    }

    public static void main(String[] args) {
        PrimAr primAr = new PrimAr();
        primAr.initGraph();
        primAr.prim(0);
        int[] path1 = primAr.path;
        for (int i1 = 0; i1 < path1.length; i1++) {
            System.out.print(" " + path1[i1]);
        }
    }


}
