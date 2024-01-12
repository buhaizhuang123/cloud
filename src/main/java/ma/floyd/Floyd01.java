package ma.floyd;

import ma.Graph;
import ma.dijstra.Dijkstra;

/**
 * @author haizhuangbu
 * @date 2023/9/10 20:12
 * @mark Floyd01 弗洛伊德算法
 */
public class Floyd01 {

    private Graph graph;

    private int[][] path;

    public Floyd01(Graph graph) {
        this.graph = graph;
        int[][] edges = this.graph.getEdges();
        path = new int[edges.length][edges.length];
    }

    public void floyd() {

        int[][] edges = graph.getEdges();

        for (int k = 0; k < edges.length; k++) {
            for (int i = 0; i < edges.length; i++) {
                for (int j = 0; j < edges.length; j++) {
                    if (edges[i][j] > edges[i][k] + edges[k][j] && edges[i][k] != 0 && edges[k][j] != 0) {
                        edges[i][j] = edges[i][k] + edges[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }

    }


    public static void main(String[] args) {


        Dijkstra dijkstra = new Dijkstra();
        dijkstra.initGraph();
        Graph graph1 = dijkstra.graph;

        Floyd01 floyd01 = new Floyd01(graph1);
        floyd01.floyd();
//        graph1.foreach();


        int[][] path1 = floyd01.path;

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


    }

}
