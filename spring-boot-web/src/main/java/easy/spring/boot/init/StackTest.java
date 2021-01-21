package easy.spring.boot.init;

/**
 * @Author: luliangliang
 * @Date: 2021/1/2 2:09 下午
 */
public class StackTest {
    public static void main(String[] args) {
        int i = 0;
        int x = i++;
        int y = ++i;
        System.out.println(x);
        System.out.println(y);
    }
}
