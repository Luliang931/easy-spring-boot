package easy.spring.boot.event.springboot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: luliangliang
 * @Date: 2021/2/22 5:22 下午
 */
public class ApplicationEventBootstrap {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // 注册监听器
        ctx.addApplicationListener(new MyApplicationListener());

        // 基于注解监听器注册
        ctx.register(MyApplicationListener2.class);

        //启动上下文
        ctx.refresh();

        //发布事件
        ctx.publishEvent(new MyApplicationEvent(new Object()));

        ctx.close();


    }
}
