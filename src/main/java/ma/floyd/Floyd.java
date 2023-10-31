package ma.floyd;

import ma.Graph;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2023/9/8 13:44
 * @mark Floyd 弗洛伊德算法
 */
public class Floyd {

    public Graph g;

    public int[][] path;

    private int max;

    public Floyd(int max) {
        this.g = new Graph(max);
        path = new int[max][max];
        this.max = max;
        initGroup();
    }


    public void initGroup() {
        int[][] edges = g.getEdges();
        for (int[] edge : edges) {
            Arrays.fill(edge, Integer.MAX_VALUE);
        }
        g.addEdge(0, 1, 2);
        g.addEdge(0, 2, 4);
        g.addEdge(0, 4, 6);
        g.addEdge(1, 2, 5);
        g.addEdge(1, 3, 7);
        g.addEdge(2, 4, 5);
        g.addEdge(4, 3, 9);
    }

    public void floyd() {
        int[][] edges = g.getEdges();
        for (int k = 0; k < max; k++) {
            for (int i = 0; i < max; i++) {
                for (int j = 0; j < max; j++) {
                    if (edges[i][k] != Integer.MAX_VALUE && edges[k][j] != Integer.MAX_VALUE &&
                            edges[i][j] > edges[i][k] + edges[k][j]) {
                        edges[i][j] = edges[i][k] + edges[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        Floyd floyd = new Floyd(5);
        floyd.floyd();
        floyd.g.foreach();

        int[][] path1 = floyd.path;

        for (int i = 0; i < path1.length; i++) {
            for (int h = 0; h < path1[i].length; h++) {
                System.out.print("[" + i + "," + h + "] ");
            }
            System.out.println();
            for (int h = 0; h < path1[i].length; h++) {
                System.out.print("  " + path1[i][h] + "   ");
            }
            System.out.println();
        }
        floyd.g.foreach();
    }


}

