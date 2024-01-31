package graphArithmetic.arithmetic.shortestPath;

import graphArithmetic.Graph;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2024/1/25 16:04
 * @mark Dijkstra
 */
public class Dijkstra {

    public Graph createGraph() {
        Graph graph = new Graph(5);
        // 记录 起点 到 当前节点 最短的边路径
        minVer = new int[5];
        // 记录 哪些顶点已经被访问过
        visited = new boolean[5];
        // 默认 所有节点未被访问
        Arrays.fill(visited, false);
        // 默认 起点 到 当前节点的最短路径是 无穷大. 方便第一次访问时,节点值更新
        Arrays.fill(minVer, Integer.MAX_VALUE);
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 3, 22);

        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 4, 6);

        graph.addEdge(2, 3, 5);
        graph.addEdge(2, 4, 7);

        graph.addEdge(3, 1, 8);
        graph.addEdge(3, 4, 6);


        graph.addEdge(4, 0, 18);

        return graph;
    }

    public int[] minVer;

    public boolean[] visited;

    public void shortWay(Graph graph) {
        // 边
        int[][] edges = graph.getEdges();
        // -1 代表默认,默认没有 剩余访问节点,退出递归
        int node = findMin();
        if (node != -1) {
            // 标记当前顶点被访问  不再重复访问
            visited[node] = true;
            for (int endNode = 0; endNode < edges[node].length; endNode++) {
                // !visited[endNode]
                // minVer[endNode] > minVer[node] + edges[node][endNode]
                // 起始点到当前节点的距离 < 图中 起始点到当前节点的父节点距离 + 父节点到当前节点的距离(更新为最新的相对短路径)
                if (edges[node][endNode] != 0 && !visited[endNode] && minVer[endNode] > minVer[node] + edges[node][endNode]) {
                    minVer[endNode] = minVer[node] + edges[node][endNode];
                }
            }
            shortWay(graph);
        }


    }

    // 入口方法
    public void min(int start, Graph graph) {
        // 首先设置 起点路径为0,从起点开始遍历
        minVer[start] = 0;
        shortWay(graph);
    }


    private int findMin() {
        int min = -1;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < minVer.length; i++) {
            if (minValue > minVer[i] && !visited[i]) {
                minValue = minVer[i];
                min = i;
            }
        }
        return min;
    }


    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        Graph graph = dijkstra.createGraph();
        dijkstra.min(0, graph);
        Arrays.stream(dijkstra.minVer).forEach(System.out::println);
    }


}
