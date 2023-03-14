package com.cloud.util;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author haizhuangbu
 * @date 2023/3/3 10:42
 * @mark LockUtils
 */
public class LockUtils {

    public static void main(String[] args) throws InterruptedException {
        /*LongAdder longAdder = new LongAdder();
        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left + right;
            }
        }, 2);
        longAccumulator.accumulate(2);*/



        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379");
        config.setCodec(new JsonJacksonCodec());
        RedissonClient client = Redisson.create(config);
        RLock rLock = client.getLock("xxx");
        boolean b = rLock.tryLock(20000, TimeUnit.MINUTES);
        if (b){
            System.out.println(" = " + "加锁成功");
        }else System.out.println(" = " + "加锁失败");

    }

}
