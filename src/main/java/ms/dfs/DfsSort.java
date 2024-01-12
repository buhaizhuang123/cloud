package ms.dfs;


import com.sun.istack.internal.logging.Logger;

/**
 * @author haizhuangbu
 * @date 2023/6/5 08:50
 * @mark DfsSort
 */
public class DfsSort {

    static Logger logger = Logger.getLogger(DfsSort.class);

    public static void dfs(int[][] graph, int start, boolean[] visited) {

        visited[start] = true;
        logger.info("start " + start);


        for (int i = 0; i < graph[start].length; i++) {
            logger.info(String.format(" 当前遍历元素 : %s , 横轴 : %s , 纵轴 : %s", graph[start][i], start, i));

            if (start == graph.length - 1 && i == graph[start].length - 1) {
                break;
            }

            if (i == (graph[start].length - 1)) {
                start++;
            }

            if (start < graph[0].length && !visited[start]) {
                logger.info(String.format(" start : %s", start));
                dfs(graph, start, visited);
            }


        }

    }


    public static void main(String[] args) {
        int[][] graph = {
                {1, 3, 5},
                {2, 4, 7},
                {9, 8, 9},
        };
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        System.out.println("graph - size : " + graph.length);

        dfs(graph, 0, visited);

    }

}
