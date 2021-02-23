package agent.demo;

/**
 * @Author: luliangliang
 * @Date: 2021/1/27 7:16 下午
 */
public class JavaAgent {
    public static void main(String[] args) {
        System.out.println("main start");


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main end");
    }
}
