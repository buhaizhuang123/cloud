package ms;

/**
 * @author haizhuangbu
 * @date 2023/6/7 08:36
 * @mark DemoMs
 */
public class DemoMs {

    // 一只青蛙一次可以跳一级台阶 也可以跳两级台阶
    // 问：一只青蛙跳上n级台阶有多少种方法
    public static int up(int n) {
        // 一级与0级台阶只有一种方法
        if (n == 1 || n == 0) return 1;
        return up(n - 1) + up(n - 2);
    }

    private static int[] moneyTyp = new int[]{2, 5, 7};

    // 动态规划 求硬币 最少硬币
    public static int m(int x) {


        return Math.min(Math.min((money(x - 2) + 1), money(x - 5) + 1), money(x - 7) + 1);
    }

    public static int money(int x) {


        return 1;
    }

    // {2,5,7}
    public static int coinChange(int[] a, int m) {
        int[] f = new int[m + 1];
        int n = a.length;
        f[0] = 0;
        int i, j;
        for (i = 1; i <= m; ++i) {
            f[i] = Integer.MAX_VALUE;
            for (j = 0; j < n; ++j) {
                if (i >= a[j] && f[i - a[j]] != Integer.MAX_VALUE) {
                    f[i] = Math.min(f[i - a[j]] + 1, f[i]);
                }
            }
        }

        if (f[m] == Integer.MAX_VALUE) {
            f[m] = -1;
        }
        return f[m];

    }


    public static void main(String[] args) {
        int up = DemoMs.up(3);
        System.out.printf("青蛙跳上10级台阶有%s种方法%n", up);

        int [] a = new int[]{2,5,7};
        int i = coinChange(a, 27);
        System.out.println("i = " + i);

    }

}
