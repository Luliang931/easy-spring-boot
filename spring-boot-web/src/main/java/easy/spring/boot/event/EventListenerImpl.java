package easy.spring.boot.event;

/**
 * @Author: luliangliang
 * @Date: 2021/2/22 11:48 上午
 */
public class EventListenerImpl implements EventListener{
    @Override
    public void onChange(Event t) {
        System.out.println("监听实现。。。" + t.toString());
    }
}
