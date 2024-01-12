package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import proxy.impl.OneBase;

import java.lang.reflect.Method;

/**
 * @author haizhuangbu
 * @date 2023/8/24 17:45
 * @mark LocalRunning
 */
public class LocalRunning implements MethodInterceptor {
    private Object object;

    public LocalRunning(Object o) {
        this.object = o;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(" before");
        Object invoke = methodProxy.invoke(object, objects);
        System.out.println(" after");
        return invoke;


    }
}
