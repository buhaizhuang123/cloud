package singleton;

public enum EnumSingletonPattern {

    INSTANCE;


    public static EnumSingletonPattern getInstance() {
        return EnumSingletonPattern.INSTANCE;
    }


    public static void main(String[] args) {
        EnumSingletonPattern instance = EnumSingletonPattern.getInstance();
        EnumSingletonPattern instance1 = EnumSingletonPattern.getInstance();
        System.out.println(instance1 == instance);
    }
}
