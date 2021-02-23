package easy.spring.boot.event;

import java.util.HashSet;
import java.util.Set;

/**
 * 事件管理器(事件源)
 *
 * @Author: luliangliang
 * @Date: 2021/2/22 11:51 上午
 */
public class EventManager {

    // 监听器集合
    private Set<EventListener> listeners = new HashSet<>();


    /**
     * 添加监听器
     * @param listener
     */
    public void addListener(EventListener listener){
        if (listener == null){
            return;
        }

        listeners.add(listener);
    }


    /**
     * 触发事件
     *
     * @param event
     */
    public void postEvent(Event event){
        if (event == null){
            return;
        }

        notifyAllListeners(event);
    }

    /**
     * 通知所有监听器
     * @param event
     */
    private void notifyAllListeners(Event event){
        for (EventListener listener : listeners) {
            listener.onChange(event);
        }
    }


    public static void main(String[] args) {
        EventManager eventManager = new EventManager();

        EventListener listener = new EventListenerImpl();
        eventManager.addListener(listener);
        eventManager.addListener((t) -> {
            System.out.println("listener1");
        });

        Event event = new Event(new Object(),"test");

        eventManager.postEvent(event);
    }
}
