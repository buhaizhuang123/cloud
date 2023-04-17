package com.design.single;


/**
 * @author haizhuangbu
 * @date 2023/4/17 10:09
 * @mark Single 单例模式
 */
public class Single {


    public static void main(String[] args) {
        // hungry Single
        HungerSingleModel hunger01 = HungerSingleModel.getInstance();
        HungerSingleModel hunger02 = HungerSingleModel.getInstance();
        System.out.println(hunger01.equals(hunger02));

        // lazy Single
        LazySingleModel lazySingleModel01 = LazySingleModel.getInstance();
        LazySingleModel lazySingleModel02 = LazySingleModel.getInstance();
        System.out.println(lazySingleModel01.equals(lazySingleModel02));

        // static Single
        StaticInSingleModel staticInSingleModel01 = StaticInSingleModel.INSTANCE.getInstance();
        StaticInSingleModel staticInSingleModel02 = StaticInSingleModel.INSTANCE.getInstance();
        System.out.println(staticInSingleModel01.equals(staticInSingleModel02));

        // enum Single
        SingleEnum.INSTANCE.setSingle(new Single());

        Single single01 = SingleEnum.INSTANCE.getSingle();
        Single single02 = SingleEnum.INSTANCE.getSingle();
        System.out.println(single02.equals(single01));
    }

}


/**
 * 饿汉单例 不存在线程安全问题
 * 缺点: 资源浪费
 * 解决方法: 在构造方法中添加校验、单例对象非空时、再初始化报错
 */
class HungerSingleModel {

    private static HungerSingleModel single = new HungerSingleModel();

    private HungerSingleModel() {
    }

    public static HungerSingleModel getInstance() {
        return single;
    }
}

/**
 * 懒汉单例
 * 缺点: 存在线程安全问题,解决方法：加锁、排他锁、双重校验锁
 */
class LazySingleModel {

    private static LazySingleModel lazySingleModel;


    private LazySingleModel() {
    }

    public static LazySingleModel getInstance() {
        if (lazySingleModel == null) lazySingleModel = new LazySingleModel();
        return lazySingleModel;
    }

    public static LazySingleModel getInstanceLock() {
        synchronized (lazySingleModel) {
            if (lazySingleModel == null) lazySingleModel = new LazySingleModel();
            return lazySingleModel;
        }

    }

}


/**
 * 静态内部单例
 * 缺点: 被反射破坏
 */
class StaticInSingleModel {

    public static class INSTANCE {

        private static final StaticInSingleModel staticInSingleModel = new StaticInSingleModel();


        public static StaticInSingleModel getInstance() {
            return staticInSingleModel;
        }
    }
}


enum SingleEnum {
    INSTANCE;
    private Single single;

    public Single getSingle() {
        return single;
    }

    public void setSingle(Single single) {
        this.single = single;
    }
}



