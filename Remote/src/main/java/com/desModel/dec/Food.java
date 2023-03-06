package com.desModel.dec;

/**
 * @author haizhuangbu
 * @date 2023/3/5 20:50
 * @mark Food
 */
public class Food implements Canteen {

    private Food food;

    public Food(Food food) {
        this.food = food;
    }

    @Override
    public Food addFood(Food food) {
        this.food = food;
        return this;
    }

    @Override
    public void eat() {
        if (food != null) {
            food.eat();
        } else {
            System.out.println("食物准备完毕!!!");
        }
    }


}
