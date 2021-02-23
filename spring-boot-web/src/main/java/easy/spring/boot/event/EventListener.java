package easy.spring.boot.event;

/**
 * 时间监听接口
 *
 * @Author: luliangliang
 * @Date: 2021/2/22 11:46 上午
 */
public interface EventListener {

    void onChange(Event t);
}
