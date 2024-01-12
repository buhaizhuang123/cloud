package str;

/**
 * @author haizhuangbu
 * @date 2023/9/14 10:08
 * @mark BfStr
 */
public class BfStr {

    public static void main(String[] args) {

        String target = "cabacababababababaacc";

        String model = "abababa";

        for (int i = 0; i < target.length(); i++) {

            for (int j = 0; j < model.length(); j++) {
                // 不相同 跳过本次
                if (target.charAt(i + j) != model.charAt(j)) {
                    break;
                }

                if (j == model.length() - 1) {
                    System.out.println("匹配成功" + i + ":" + (i + j));
                }


            }

        }


    }

}
