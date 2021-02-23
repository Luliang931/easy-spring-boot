package easy.spring.boot.proxy;

import java.lang.reflect.Proxy;

/**
 * @Author: luliangliang
 * @Date: 2021/2/22 8:29 下午
 */
public class ProxyMain {
    public static void main(String[] args) {
        Subject subject = new SubjectImpl();

        // 创建代理对象
        SubjectProxy subjectProxy = new SubjectProxy(subject);

        // 实例化代理对象
        Subject proxy = (Subject)Proxy.newProxyInstance(subjectProxy.getClass().getClassLoader(), subject.getClass().getInterfaces(), subjectProxy);

        proxy.execute("test");
    }
}
