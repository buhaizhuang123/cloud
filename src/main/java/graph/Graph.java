package graph;

import java.util.*;

/**
 * @author haizhuangbu
 * @date 2023/5/30 13:33
 * @mark Graph 图 深度优先遍历算法
 */
public class Graph {


    // 存储所有顶点
    private ArrayList<String> vertexList;
    // 需要一个数组存储所有连接矩阵
    private int[][] edges;
    // 边的个数
    private int numofEdges;

    private boolean[] isVisited;

    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        isVisited = new boolean[n];
        this.numofEdges = 0;
    }

    // 添加顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }


    // 插入边
    public void insertEdges(int e1, int e2, int weight) {
        edges[e1][e2] = weight;
        edges[e2][e1] = weight;
        numofEdges++;
    }

    // 返回顶点个数
    public int getVertexList() {
        return vertexList.size();
    }

    // 返回边的个数
    public int getNumofEdges() {
        return numofEdges;
    }

    public String getValueIndex(int index) {
        return vertexList.get(index);
    }

    // 获取邻接矩阵下标的值
    public int getWeight(int e1, int e2) {
        return edges[e1][e2];
    }

    // 显示矩阵
    public void showGroup() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    // 获取第一个邻接点的下标
    public int getFirstNeighbor(int idx) {
        for (int i = 0; i < getVertexList(); i++) {
            if (edges[idx][i] > 0) {
                return i;
            }
        }
        return -1;
    }


    // 获取下一个邻接点的下标
    public int getNextNeighbor(int e1, int e2) {
        for (int i = e2 + 1; i < vertexList.size(); i++) {
            if (edges[e1][i] > 0) return i;
        }
        return -1;
    }


    // 深度优先遍历实现
    public void dfs(boolean[] isVisited, int idx) {

        System.out.println(getValueIndex(idx));

        isVisited[idx] = true;

        int w = getFirstNeighbor(idx);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }

            w = getNextNeighbor(idx, w);
        }


    }


    public void dfs() {

        for (int i = 0; i < getVertexList(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }

        }


    }


    // 广度优先遍历
    public void bfs(boolean[] isVisited, int idx, int first) {
        if (idx == first && !isVisited[idx]) {
            System.out.println(getValueIndex(idx));
            isVisited[idx] = true;
            int head = getFirstNeighbor(idx);
            bfs(isVisited, idx, head);
        }
        if (!isVisited[first]) {
            System.out.println(getValueIndex(first));
            isVisited[first] = true;
        }
        int nextNeighbor = getNextNeighbor(idx, first);
        if (nextNeighbor != -1) {
            bfs(isVisited, idx, nextNeighbor);
        }
    }


    public void bfs() {
        for (int i = 0; i < getVertexList(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i, i);
            }
        }
    }


    public static void main(String[] args) {
        int n = 5;
        Graph graph = new Graph(n);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(0, 3, 1);
        graph.insertEdges(1, 4, 1);
        graph.insertEdges(2, 4, 1);
        graph.insertEdges(3, 4, 1);

        graph.showGroup();
        graph.dfs();
        graph.isVisited = new boolean[graph.isVisited.length];
        System.out.println();
        graph.bfs();
    }

}
