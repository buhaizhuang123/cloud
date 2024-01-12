package str;

/**
 * @author haizhuangbu
 * @date 2023/9/14 10:34
 * @mark BmStr
 */
public class BmStr {


    public static void main(String[] args) {

        String target = "abababcacbsba";

        String model = "abc";

        for (int i = 0; i < target.length() - model.length(); i++) {
            String substring = target.substring(i, i + model.length());

            if (model.hashCode() == substring.hashCode()) {
                System.out.println("i : " + i + " : " + (i + model.length()));
            }
        }
    }

}
