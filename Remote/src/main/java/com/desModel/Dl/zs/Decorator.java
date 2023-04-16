package com.desModel.Dl.zs;

import com.desModel.Dl.iCom.Component;

/**
 * @author haizhuangbu
 * @date 2023/4/16 16:44
 * @mark Decorator
 */
public class Decorator implements Component {

    private Component component;

    public Decorator(Component com) {
        this.component = com;
    }

    @Override
    public void operation() {
        this.component.operation();
    }
}
