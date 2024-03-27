package singleton;

/**
 * @author haizhuangbu
 * @date 2024/1/23 17:54
 * @mark StaticInnerSingletonPattern
 */
public class StaticInnerSingletonPattern {

    private StaticInnerSingletonPattern() {
    }
    public static class Builder {
        private static final StaticInnerSingletonPattern staticInnerSingletonPattern = new StaticInnerSingletonPattern();


    }

    public static StaticInnerSingletonPattern getInstance() {
        return Builder.staticInnerSingletonPattern;
    }


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        StaticInnerSingletonPattern instance = StaticInnerSingletonPattern.getInstance();

        StaticInnerSingletonPattern staticInnerSingletonPattern = StaticInnerSingletonPattern.class.newInstance();
        System.out.println(staticInnerSingletonPattern == instance);
    }

}
