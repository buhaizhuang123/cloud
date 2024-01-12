package graph;

/**
 * @author haizhuangbu
 * @date 2023/5/30 15:53
 * @mark DeepPrioritySort 深度优先算法
 */
public class DeepPrioritySort {


    // 深度优先算法
    public void exists(char[][] arr, String word) {
        boolean[][] booleans = new boolean[arr.length][arr[0].length];
        for (int x = 0; x < arr.length; ++x) {
            for (int y = 0; y < arr[0].length; ++y) {
                if (dsf(arr, word, x, y, 0, booleans)) {
                    System.out.println(arr[x][y]);
                }
            }
        }

    }

    private Boolean dsf(char[][] arr, String word, int x, int y, int index, boolean[][] booleans) {
        if (x < 0 || y < 0 || x >= arr.length || y >= arr[0].length) return false;
        if (booleans[x][y] || arr[x][y] != word.charAt(index)) return false;
        if (index == word.length() - 1) return true;
        booleans[x][y] = true;
        System.out.println(arr[x][y]);
        if (dsf(arr, word, x + 1, y, index + 1, booleans)
                || dsf(arr, word, x - 1, y, index + 1, booleans)
                || dsf(arr, word, x, y + 1, index + 1, booleans) ||
                dsf(arr, word, x, y - 1, index + 1, booleans)) return true;
        booleans[x][y] = false;
        return false;

    }


    public static void main(String[] args) {

        char[][] arr = new char[5][5];
        arr[0][0] = 'a';
        arr[0][1] = 'b';
        arr[0][2] = 'c';
        arr[0][3] = 'd';
        arr[0][4] = 'e';

        arr[1][0] = 'a';
        arr[1][1] = 'b';
        arr[1][2] = 'c';
        arr[1][3] = 'd';
        arr[1][4] = 'e';

        arr[2][0] = 'a';
        arr[2][1] = 'b';
        arr[2][2] = 'c';
        arr[2][3] = 'd';
        arr[2][4] = 'e';

        arr[3][0] = 'a';
        arr[3][1] = 'b';
        arr[3][2] = 'c';
        arr[3][3] = 'd';
        arr[3][4] = 'e';


        arr[4][0] = 'a';
        arr[4][1] = 'b';
        arr[4][2] = 'c';
        arr[4][3] = 'd';
        arr[4][4] = 'e';

        new DeepPrioritySort().exists(arr, "word");

    }

}
