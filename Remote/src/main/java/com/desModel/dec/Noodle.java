package com.desModel.dec;

/**
 * @author haizhuangbu
 * @date 2023/3/5 20:53
 * @mark Noodle
 */
public class Noodle extends Food {

    public Noodle(Food food) {
        super(food);
    }

    public Noodle() {
        super(null);
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("面条");
    }
}
