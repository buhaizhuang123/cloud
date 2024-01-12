package com.desModel.Dl.iCom.impl;

import com.desModel.Dl.iCom.Component;

/**
 * @author haizhuangbu
 * @date 2023/4/16 16:44
 * @mark ConcreteComponet
 */
public class ConcreteComponet implements Component {


    @Override
    public void operation() {
        System.out.println("基础组件");
    }
}
