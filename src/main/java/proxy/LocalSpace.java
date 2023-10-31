package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author haizhuangbu
 * @date 2023/8/24 17:29
 * @mark LocalSpace
 */
public class LocalSpace implements InvocationHandler {

    private Object object;

    public LocalSpace(Object o) {
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(object, args);
        after();
        return invoke;
    }

    public void before() {
        System.out.println("before");
    }

    public void after() {
        System.out.println("after");
    }

}
