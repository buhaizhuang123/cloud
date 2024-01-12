package com.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author haizhuangbu
 * @date 2022/7/26 10:25
 * @mark SyncLock
 */
public class SyncLock {


    private ReentrantLock reentrantLock = new ReentrantLock();



    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public void lock(){
        reentrantLock.lock();

        reentrantLock.unlock();

        Lock lock = readWriteLock.readLock();

        lock.lock();

        Lock lock1 = readWriteLock.writeLock();

        reentrantLock.unlock();


    }


    public static void main(String[] args) {

        int i = 1;

        int z = 1;
        if (i == 1) {
            System.out.println(i);
            i = i+1;
        }else if (z == 1){
            System.out.println(i);
        }


    }

}
