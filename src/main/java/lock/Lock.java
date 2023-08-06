package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author haizhuangbu
 * @date 2023/7/27 13:51
 * @mark Lock
 */
public class Lock {

    private ReentrantLock lock = new ReentrantLock();


    public void testLock() {

        lock.lock();

        lock.unlock();


    }


}
