package com.desModel.Dl.zs.impl;

import com.desModel.Dl.iCom.Component;
import com.desModel.Dl.zs.Decorator;

/**
 * @author haizhuangbu
 * @date 2023/4/16 16:47
 * @mark ConcreteComponentA
 */
public class ConcreteComponentA extends Decorator {
    public ConcreteComponentA(Component com) {
        super(com);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("添加组件A");
    }
}
