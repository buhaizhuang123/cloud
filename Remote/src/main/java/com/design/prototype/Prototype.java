package com.design.prototype;

/**
 * @author haizhuangbu
 * @date 2023/4/17 10:51
 * @mark Prototype 原型模式
 */
public class Prototype implements Cloneable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Prototype prototype = new Prototype();
        prototype.setName("p1");
        try {
            Prototype p1 =  (Prototype)prototype.clone();
            System.out.println(p1.getName());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

}
