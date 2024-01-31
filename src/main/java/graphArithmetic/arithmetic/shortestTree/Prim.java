package graphArithmetic.arithmetic.shortestTree;

import graphArithmetic.Graph;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2024/1/31 09:56
 * @mark Prim 普雷姆算法
 */
public class Prim {

    private Graph graph;

    private int[] minimum;
    private int[] path;

    private boolean[] visited;

    public Prim(Graph graph) {
        this.graph = graph;
        minimum = new int[graph.getEdges().length];
        visited = new boolean[graph.getEdges().length];
        path = new int[graph.getEdges().length];
        Arrays.fill(minimum, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

    }

    public void prim(int start) {
        minimum[start] = 0;
        prim();
        Arrays.stream(path).forEach(System.out::println);
    }


    public void prim() {
        // 最小节点
        int minNode = findMin();
        if (minNode != -1) {
            // 防止重复访问
            visited[minNode] = true;
            int[][] edges = graph.getEdges();
            for (int i = 0; i < edges[minNode].length; i++) {
                // !visited[i] 防止环形成
                if (edges[minNode][i] != 0 && edges[minNode][i] < minimum[i] && !visited[i]) {
                    minimum[i] = edges[minNode][i];
                    path[i] = minNode;
                }
            }
            prim();
        }

    }

    private int findMin() {
        int minVal = Integer.MAX_VALUE;
        int min = -1;
        for (int i = 0; i < minimum.length; i++) {
            if (minVal > minimum[i] && !visited[i]) {
                minVal = minimum[i];
                min = i;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Graph graph1 = new Graph(4);

        graph1.addEdge(0, 1, 1);
        graph1.addEdge(0, 2, 3);
        graph1.addEdge(1, 2, 1);
        graph1.addEdge(2, 1, 4);
        graph1.addEdge(2, 3, 1);
        graph1.addEdge(3, 1, 2);
        graph1.addEdge(3, 0, 7);
        graph1.addEdge(3, 2, 1);

        Prim prim = new Prim(graph1);
        prim.prim(0);

    }


}
