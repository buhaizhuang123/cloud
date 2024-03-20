package com.cloud.mail.controller;

/**
 * @author haizhuangbu
 * @date 2024/3/1 17:36
 * @mark AddCount
 */
public class AddCount {

    private static int i = 0;

    private AddCount() {
    }


    public synchronized static void add() {
        i++;
    }

    public static int get() {
        return i;
    }


}
