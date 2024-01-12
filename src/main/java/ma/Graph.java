package ma;

import java.util.HashMap;
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
                System.out.print("  " + (edges[i][h] == Integer.MAX_VALUE ? "INF" : edges[i][h]) + "   ");
            }
            System.out.println();
        }
    }


    public int[][] getEdges() {
        return edges;
    }

    public void setEdges(int[][] edges) {
        this.edges = edges;
    }

    public boolean isPalindrome(int x) {
        String idx = String.valueOf(x);
        if (idx.length() < 2) return true;
        int i = 0;
        int j = idx.length() - 1;
        while (j > 0 && i < idx.length()) {
            if (!(idx.charAt(i) == idx.charAt(j))) {
                return false;
            }
            j--;
            i++;
        }
        return true;
    }

    public String longestCommonPrefix(String[] strs) {

        String str = strs[0];
        if (str.length() < 1) {
            return str;
        }
        boolean flag = true;
        int idx = 0;
        while (flag && idx < str.length()) {
            char c = str.charAt(idx);
            if (strs.length < 2) {
                idx++;
                break;
            }
            for (int i = 1; i < strs.length; i++) {

                if (strs[i].length() <= idx || !(strs[i].charAt(idx) == c)) {
                    idx--;
                    flag = false;
                    break;
                }
            }
            idx++;
        }

        return str.substring(0, idx);
    }

    public boolean isValid(String s) {
        // () [] {}
        HashMap<String, String> map = new HashMap<>();
        map.put("(", ")");
        map.put(")", "(");
        map.put("[", "]");
        map.put("]", "[");
        map.put("{", "}");
        map.put("}", "{");
        if (s.length() % 2 > 0) return false;
        boolean flag = true;
        for (int i = s.length() / 2; i > 0; i--) {

            for (int j = 0; j < s.length() / 2; j++) {
                if (!(map.get(String.valueOf(s.charAt(j))).equals(String.valueOf(s.charAt(j + i))))) flag = false;
            }


        }

        return flag;
    }


    public static void main(String[] args) {
        Graph graph = new Graph(2);
        String[] s = new String[]{"ab", "a"};
        String s1 = graph.longestCommonPrefix(s);
        System.out.println(s1);

        boolean valid = graph.isValid("()");
        System.out.printf("" + valid);

    }
}
