package easy.spring.boot.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: luliangliang
 * @Date: 2021/2/22 8:24 下午
 */
public class SubjectProxy implements InvocationHandler {

    private Subject subject;

    public SubjectProxy(Subject subject){
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理对象方法执行开始");
        // 利用反射调用实际方法
        Object invoke = method.invoke(subject, args);
        System.out.println("代理对象方法执行结束");

        return invoke;
    }
}
