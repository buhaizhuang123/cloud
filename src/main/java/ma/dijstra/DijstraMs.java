package ma.dijstra;

import ma.Graph;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2023/9/6 15:36
 * @mark DijstraMs 迪杰斯特拉算法
 */
public class DijstraMs {

    // 已访问节点
    public boolean[] visited;

    // 开始顶点到其他节点的最短路径
    public int[] v_o;

    private Graph graph;

    public DijstraMs() {
        graph = new Graph(5);
        visited = new boolean[5];
        v_o = new int[5];
    }

    public void init() {

        graph.addEdge(0, 0, 999);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 10);
        graph.addEdge(0, 4, 20);
        graph.addEdge(1, 1, 999);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 4, 5);
        graph.addEdge(4, 3, 9);
        graph.addEdge(4, 4, 999);
        Arrays.fill(v_o, 999);
    }

    public void dijstra(int v) {
        init();
        // 边关系
        int[][] edges = graph.getEdges();
        v_o[v] = 0;
        // 节点中权重最小的边
        int minV = v;
        while (!visited[minV]) {
            visited[minV] = true;
            int[] edge = edges[minV];
            int min = minV;
            // 将顶点与子节点之间的距离更新到距离中
            for (int i = 0; i < edge.length; i++) {
                if (visited[i]) continue;
                int i1 = edge[i];
                if (i1 > 0 && v_o[i] > v_o[minV] + i1) {
                    v_o[i] = v_o[minV] + i1;
                    if (edge[min] > v_o[minV] + i1) {
                        min = i;
                    }
                }
            }
            if (min != minV) minV = min;
        }

        Arrays.stream(v_o).forEach(i -> System.out.print(i + " "));
        System.out.println();

    }


    public static void main(String[] args) {

        DijstraMs dijstraMs = new DijstraMs();
        dijstraMs.dijstra(0);

    }

}
