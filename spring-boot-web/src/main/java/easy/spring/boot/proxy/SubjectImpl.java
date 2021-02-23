package easy.spring.boot.proxy;

/**
 * @Author: luliangliang
 * @Date: 2021/2/22 8:24 下午
 */
public class SubjectImpl implements Subject{
    @Override
    public void execute(String execute) {
        System.out.println("接口实现：" + execute);
    }
}
