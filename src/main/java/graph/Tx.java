package graph;

import java.util.Scanner;

/**
 * @author haizhuangbu
 * @date 2023/5/30 14:13
 * @mark Tx
 */
public class Tx {
    // 币种
    static int[] money = {100, 50, 20, 10, 5, 1};
    // 币数量
    static int[] sum = new int[6];

    public static void main(String[] args) {

        int random = 1001;
        for (int i = 0; i < money.length; i++) {
            int count = random / money[i];
            sum[i] = count;
            random = random % money[i];
        }

        for (int i = 0; i < sum.length; i++) {

            System.out.println(money[i] + ":" + sum[i]);

        }


    }

}
