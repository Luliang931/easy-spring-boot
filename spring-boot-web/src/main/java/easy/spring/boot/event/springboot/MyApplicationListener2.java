package easy.spring.boot.event.springboot;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author: luliangliang
 * @Date: 2021/2/22 5:27 下午
 */
@Component
public class MyApplicationListener2 {

    @EventListener(value = MyApplicationEvent.class)
    public void onEvent(MyApplicationEvent event){
        System.out.println("注解监听器。。。"+event.toString());
    }
}
