package com.cloud.chain.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author haizhuangbu
 * @date 2022/6/20 15:29
 * @mark PersonChain
 */
public abstract class PersonChain {

    public PersonChain personChain;

    private Logger logger = LoggerFactory.getLogger(PersonChain.class);


    public void next(PersonChain personChain) {
        this.personChain = personChain;
    }


    public void doExecute() {
        logger.info("人物校验主方法");
        if (personChain != null) {
            logger.info("执行人物校验子方法");
            personChain.doExecute();
        }
    }


    public static class Builder {

        private PersonChain head;

        private PersonChain tail;

        public Builder next(PersonChain personChain) {

            if (head == null) {
                this.head = this.tail = personChain;
                return this;
            }
            this.head.next(personChain);
            this.tail = personChain;
            return this;
        }

        public PersonChain build() {
            return this.head;
        }

    }

    public static void main(String[] args) {
        sned("123","123");
    }

    public static void sned(String... data){
        System.out.println(data);
    }

}
