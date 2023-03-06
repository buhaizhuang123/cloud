package com.desModel.dec.ts;

import com.desModel.dec.Food;
import com.desModel.dec.Noodle;
import com.desModel.dec.Rice;

/**
 * @author haizhuangbu
 * @date 2023/3/5 20:56
 * @mark FoodTs
 */
public class FoodTs {

    public static void main(String[] args) {

        Food food = new Food(new Rice(new Noodle()));
        food.eat();



    }

}
