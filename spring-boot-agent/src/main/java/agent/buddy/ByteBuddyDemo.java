package agent.buddy;


import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @Author: luliangliang
 * @Date: 2021/2/1 5:45 下午
 */
public class ByteBuddyDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        String helloWorld = new ByteBuddy()
                .subclass(Object.class)
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(ByteBuddyDemo.class.getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();

        System.out.println(helloWorld);
    }
}
