package com.desModel.dec;

/**
 * @author haizhuangbu
 * @date 2023/3/5 20:55
 * @mark Rice 米饭
 */
public class Rice extends Food {

    public Rice(Food food) {
        super(food);
    }

    public Rice() {
        super(null);

    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("米饭");
    }
}
