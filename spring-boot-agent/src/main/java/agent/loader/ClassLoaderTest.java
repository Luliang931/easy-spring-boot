package agent.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author: luliangliang
 * @Date: 2021/2/2 8:32 下午
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String path = "/Users/luliangliang/LearnProjects/easy-spring-boot/spring-boot-agent/src/main/java/agent/loader/Demo.class";

        CustomClassLoader customClassLoader = new CustomClassLoader(path);

        Class<?> clazz = customClassLoader.loadClass("agent.loader.Demo");

        System.out.println("class loader:" + clazz.getClassLoader().getClass().getName());

        Method mainMethod = clazz.getDeclaredMethod("main", String[].class);

        Demo demo = (Demo)clazz.newInstance();
        String[] argArr = {"test class loader"};
        mainMethod.invoke(demo,(Object)argArr);
    }

}
