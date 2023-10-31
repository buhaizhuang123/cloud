package proxy.impl;

import net.sf.cglib.proxy.Enhancer;
import proxy.LocalRunning;
import proxy.LocalSpace;
import proxy.base.Base;

import java.lang.reflect.Proxy;

/**
 * @author haizhuangbu
 * @date 2023/8/24 17:36
 * @mark OneBase
 */
public class OneBase implements Base {
    @Override
    public void say() {
        System.out.println("i , M Running");
    }

    public static OneBase getProxy() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OneBase.class);
        enhancer.setCallback(new LocalRunning(new OneBase()));
        return (OneBase) enhancer.create();

    }

    public static void main(String[] args) {

        Base o = (Base) Proxy.newProxyInstance(OneBase.class.getClassLoader(), new Class[]{Base.class}, new LocalSpace(new OneBase()));
        o.say();


        OneBase oneBase = getProxy();
        oneBase.say();


    }


}
