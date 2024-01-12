package com.cloud.chain.sys.main;

import com.cloud.chain.sys.PersonChain;
import com.cloud.chain.sys.impl.MainPerson;
import com.cloud.chain.sys.impl.SpousePerson;

import java.util.concurrent.CountDownLatch;

/**
 * @author haizhuangbu
 * @date 2022/6/20 15:43
 * @mark PersonMain
 */
public class PersonMain {


    public static void main(String[] args) {

        PersonChain.Builder builder = new PersonChain.Builder();
        PersonChain build = builder.next(new MainPerson())
                .next(new SpousePerson())
                .next(new SpousePerson())
                .build();
        build.doExecute();



    }

}
