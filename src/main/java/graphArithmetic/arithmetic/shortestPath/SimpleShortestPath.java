package graphArithmetic.arithmetic.shortestPath;

import com.sun.jmx.remote.internal.ArrayQueue;
import graphArithmetic.Graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author haizhuangbu
 * @date 2024/2/1 09:22
 * @mark SImpleShortestPath
 */
public class SimpleShortestPath {

    private Graph graph;

    private boolean[] visited;

    private int[] path;

    private int[] size;

    private Queue<Integer> waitQueue;

    public SimpleShortestPath(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.getVertex().length];
        path = new int[graph.getVertex().length];
        size = new int[graph.getVertex().length];
        Arrays.fill(size, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
    }


    public void simpleShortestPath(int start) {
        waitQueue = new ArrayDeque<>(graph.getVertex().length);
        waitQueue.add(start);
        path[start] = start;
        size[start] = 0;
        visited[start] = true;
        simpleShortestPath();
        System.out.printf("");
    }


    public void simpleShortestPath() {

        Integer parentNode = waitQueue.poll();
        if (parentNode != null) {
            int[][] edges = graph.getEdges();
            for (int i = 0; i < edges[parentNode].length; i++) {
                if (edges[parentNode][i] != 0 && !visited[i]) {
                    waitQueue.add(i);
                    size[i] = size[parentNode] + 1;
                    path[i] = parentNode;
                    visited[i] = true;

                }

            }
            simpleShortestPath();
        }
    }


    public static void main(String[] args) {
        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1, 1);
        graph1.addEdge(0, 2, 1);
        graph1.addEdge(1, 2, 1);
        graph1.addEdge(1, 3, 1);
        graph1.addEdge(2, 3, 1);
        SimpleShortestPath simpleShortestPath = new SimpleShortestPath(graph1);
        simpleShortestPath.simpleShortestPath(0);


    }


}
