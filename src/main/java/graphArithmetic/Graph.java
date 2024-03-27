package graphArithmetic;

import lombok.Data;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2024/1/25 15:54
 * @mark Graph
 */
@Data
public class Graph {

    // 顶点
    private int[] vertex;

    // 边
    private int[][] edges;

    public Graph(int v) {
        // 顶点
        vertex = new int[v];
        edges = new int[v][v];
    }

    public void addEdge(int start, int end, int weight) {
        edges[start][end] = weight;
    }

}
