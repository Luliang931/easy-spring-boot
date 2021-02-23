package easy.spring.boot.event.springboot;

import org.springframework.context.ApplicationListener;

/**
 * @Author: luliangliang
 * @Date: 2021/2/22 5:21 下午
 */
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent myApplicationEvent) {
        System.out.println("收到事件:"+myApplicationEvent.toString());
    }
}
