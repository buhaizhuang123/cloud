package singleton;

/**
 * @author haizhuangbu
 * @date 2024/1/23 17:51
 * @mark HungerSingletonPattern
 */
public class HungerSingletonPattern {

    private static HungerSingletonPattern hungerSingletonPattern = new HungerSingletonPattern();


    private HungerSingletonPattern() {
    }

    public static HungerSingletonPattern getInstance() {
        return hungerSingletonPattern;
    }

    public static void main(String[] args) {
        HungerSingletonPattern instance = HungerSingletonPattern.getInstance();
        HungerSingletonPattern instance1 = HungerSingletonPattern.getInstance();
        System.out.println(instance1 == instance);
    }

}
