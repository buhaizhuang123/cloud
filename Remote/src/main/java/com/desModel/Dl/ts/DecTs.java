package com.desModel.Dl.ts;

import com.desModel.Dl.iCom.Component;
import com.desModel.Dl.iCom.impl.ConcreteComponet;
import com.desModel.Dl.zs.Decorator;
import com.desModel.Dl.zs.impl.ConcreteComponentA;
import com.desModel.Dl.zs.impl.ConcreteComponentB;

/**
 * @author haizhuangbu
 * @date 2023/4/16 16:48
 * @mark DecTs
 */
public class DecTs {


    public static void main(String[] args) {
        Component component = new ConcreteComponet();

        Decorator componentA = new ConcreteComponentA(component);

        componentA.operation();

        Decorator componentB = new ConcreteComponentB(component);

        componentB.operation();

        Decorator componentB1 = new ConcreteComponentB(componentA);

        componentB1.operation();

    }



}
