package easy.spring.boot.event.springboot;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义应用事件
 *
 * @Author: luliangliang
 * @Date: 2021/2/22 5:20 下午
 */
public class MyApplicationEvent extends ApplicationEvent {
    public MyApplicationEvent(Object source) {
        super(source);
    }
}
