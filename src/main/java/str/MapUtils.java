package str;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2024/3/26 21:34
 * @mark MapUtils
 */
public class MapUtils {


    public static void main(String[] args) {


        ArrayList<User> users = new ArrayList<>();
        users.add(new User("1", 20));
        users.add(new User("1", 20));
        users.add(new User("1", 20));
        users.add(new User("2", 40));
        users.add(new User("3", 50));
        users.add(new User("3", 10));
        Map<String, Integer> collect = users.stream().collect(Collectors.toMap(User::getUserId, User::getGrade, Integer::sum));
        System.out.println(collect);
    }

}
