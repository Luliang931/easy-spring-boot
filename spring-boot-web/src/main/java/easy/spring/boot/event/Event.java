package easy.spring.boot.event;

import java.util.EventObject;

/**
 * 事件对象
 *  Java事件机制包括三个部分：事件、事件源、事件监听器。
 *
 * @Author: luliangliang
 * @Date: 2021/2/22 11:44 上午
 */
public class Event extends EventObject {

    private String str = "";

    public Event(Object source, String str) {
        super(source);
        this.str = str;
    }

    @Override
    public String toString() {
        return "Event{" +
                "str='" + str + '\'' +
                '}';
    }
}
