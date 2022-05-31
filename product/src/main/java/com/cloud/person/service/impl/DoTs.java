package com.cloud.person.service.impl;

import com.cloud.person.service.DoThing;

/**
 * @author haizhuangbu
 * @date 2022/5/30 14:40
 * @mark DoTs
 */
public class DoTs {


    public static void main(String[] args) {

        Do.Builder builder =  new  Do.Builder();
        Do.Builder next = builder.next(new Do())
                .next(new Do()).next(new Do()).next(new Do());
        DoThing build = next.build();
        build.doThing();

    }

}
