package ari;

import lombok.AllArgsConstructor;
import lombok.Data;
import ma.Graph;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/10/10 09:11
 * @mark Kruskal 克鲁斯卡尔算法 最小生成树算法
 */
public class Kruskal {

    private Graph g;

    private List<Coll> sortList;

    private List<Coll> low;

    private int[] visited;


    public Kruskal() {
        g = new Graph(6);
        // 0 - A
        // 1 - B
        // 2 - C
        // 3 - D
        // 4 - E
        // 5 - E
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 10);
        g.addEdge(0, 4, 8);

        g.addEdge(1, 2, 4);
        g.addEdge(1, 3, 4);

        g.addEdge(2, 4, 8);
        g.addEdge(2, 5, 3);

        g.addEdge(3, 2, 5);

        g.addEdge(4, 5, 2);

        g.addEdge(5, 3, 10);


        sortList = new ArrayList<>();

        low = new ArrayList<>();

        visited = new int[6];

        for (int i = 0; i < 6; i++) {
            visited[i] = i;
        }

        // 1. 按照边权重值排序
        // 2. 取最小值
        // 3. 避免回环
    }

    public void sort() {
        int[][] edges = g.getEdges();
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] != 0) {
                    sortList.add(new Coll(i, j, edges[i][j]));
                }
            }
        }
        sortList.sort(Comparator.comparing(i -> BigDecimal.valueOf(i.getWeight())));
    }

    public void kruskal() {

        sort();

        for (int i = 0; i < sortList.size(); i++) {

            Coll coll = sortList.get(i);

            int n = find(coll.getStart());
            int m = find(coll.getEnd());

            if (n != m) {
                visited[n] = m;
                low.add(coll);
            }

        }

        int sum = low.stream().mapToInt(Coll::getWeight).sum();

        System.out.println(" 树 最小生成树  sum : " + sum);

    }

    public int find(int n) {
        while (visited[n] != n) {
            n = visited[n];
        }
        return n;
    }


    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal();
        kruskal.g.foreach();
        // 1. 排序
        kruskal.kruskal();
        // 2. 最小值

        // 2.1 校验是否发生环

        // 2.2.1 未生成环 加入

        // 2.2.2 生成环 跳过

    }

    @Data
    @AllArgsConstructor
    public static class Coll {

        private int start;

        private int end;

        private int weight;


    }


}
