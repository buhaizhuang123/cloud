package ma;

/**
 * @author haizhuangbu
 * @date 2023/9/6 08:41
 * @mark Graph
 */
public class Graph {

    // 边
    private int[][] edges;


    public Graph(int v) {
        edges = new int[v][v];
    }

    public void addEdge(int start, int end, int val) {
        edges[start][end] = val;
    }

    public void foreach() {
        for (int i = 0; i < edges.length; i++) {
            for (int h = 0; h < edges[i].length; h++) {
                System.out.print("[" + i + "," + h + "] ");
            }
            System.out.println();
            for (int h = 0; h < edges[i].length; h++) {
                System.out.print("  " + edges[i][h] + "   ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 2);
        graph.addEdge(0, 4, 2);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 0, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(2, 3, 2);
        graph.addEdge(2, 4, 2);
        graph.addEdge(3, 1, 2);
        graph.foreach();

    }

    public int[][] getEdges() {
        return edges;
    }

    public void setEdges(int[][] edges) {
        this.edges = edges;
    }
}
