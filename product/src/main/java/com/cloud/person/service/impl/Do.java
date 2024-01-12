package com.cloud.person.service.impl;

import com.cloud.person.service.DoThing;

/**
 * @author haizhuangbu
 * @date 2022/5/30 14:30
 * @mark Do 责任链模式
 */
public class Do implements DoThing {


    private DoThing aDo;

    @Override
    public void next(DoThing aDo) {
        this.aDo = aDo;
    }

    @Override
    public void doThing() {
        if (aDo != null) {
            aDo.doThing();
        }
        System.out.println("aDo");
    }

    public static class Builder {

        private DoThing head;

        private DoThing tail;

        public Builder next(DoThing aDo) {
            if (head == null) {
                this.head = this.tail = aDo;
                return this;
            }
            this.tail.next(aDo);
            this.tail = aDo;
            return this;
        }

        public DoThing build() {
            return this.head;
        }

    }


}
