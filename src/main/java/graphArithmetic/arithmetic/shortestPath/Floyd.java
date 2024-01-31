package graphArithmetic.arithmetic.shortestPath;

import graphArithmetic.Graph;
import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2024/1/31 13:13
 * @mark Floyd 弗洛伊德最短路径算法
 */
@AllArgsConstructor
public class Floyd {

    private Graph graph;


    public void floyd() {

        int[] vertex = graph.getVertex();

        int[][] edges = graph.getEdges();

        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                // k 为 中间节点,查看
                for (int k = 0; k < vertex.length; k++) {
                    if (edges[i][k] != Integer.MAX_VALUE && edges[k][j] != Integer.MAX_VALUE) {
                        // 松弛操作,如果 A,B 的距离 > AC + CA的距离,更新AB最短距离为 AC + CA
                        if (edges[i][j] > edges[i][k] + edges[k][j]) {
                            edges[i][j] = edges[i][k] + edges[k][j];
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {

        Graph g = new Graph(4);
        int[][] edges = g.getEdges();
        for (int i = 0; i < edges.length; i++) {
            Arrays.fill(edges[i], Integer.MAX_VALUE);
        }
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(0, 3, 2);
        g.addEdge(1, 2, 4);
        g.addEdge(2, 1, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 1, 1);
        Floyd floyd = new Floyd(g);
        floyd.floyd();
        System.out.println(g);

    }


}
