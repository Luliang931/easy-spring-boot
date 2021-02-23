package agent.log;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: luliangliang
 * @Date: 2021/2/1 8:01 下午
 */
public class Service {
    @Log
    public int foo(int value){
        System.out.println("foo: " + value);

        return value;
    }

    public int bar(int value){
        System.out.println("bar: " + value);

        return value;
    }

    static class LoggerAdvisor {
        @Advice.OnMethodEnter
        public static void onMethodEnter(@Advice.Origin Method method, @Advice.AllArguments Object[] arguments) {
            if (method.getAnnotation(Log.class) != null) {
                System.out.println("Enter " + method.getName() + " with arguments: " + Arrays.toString(arguments));
            }
        }

        @Advice.OnMethodExit
        public static void onMethodExit(@Advice.Origin Method method, @Advice.AllArguments Object[] arguments, @Advice.Return Object ret) {
            if (method.getAnnotation(Log.class) != null) {
                System.out.println("Exit " + method.getName() + " with arguments: " + Arrays.toString(arguments) + " return: " + ret);
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Service service = new ByteBuddy()
                .subclass(Service.class)
                .name("agent.log.ServiceImpl")
                .method(ElementMatchers.any())
                .intercept(Advice.to(LoggerAdvisor.class))
                .make()
                .load(Service.class.getClassLoader())
                .getLoaded()
                .newInstance();

        service.foo(123);
        service.bar(456);

    }
}
