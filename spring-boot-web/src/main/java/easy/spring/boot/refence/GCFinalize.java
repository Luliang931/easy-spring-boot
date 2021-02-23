package easy.spring.boot.refence;

import java.lang.ref.SoftReference;

/**
 * @Author: luliangliang
 * @Date: 2021/2/20 6:06 下午
 */
public class GCFinalize {

    @Override
    protected void finalize() throws Throwable{
        System.out.println(GCFinalize.class.getSimpleName()+"被回收了。。。");
    }

    public static void main(String[] args) {
        //1、强引用
        /*GCFinalize gcFinalize = new GCFinalize();
        gcFinalize = null;
        System.gc();*/


        //2、软引用
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(softReference.get());
        System.gc();
        System.out.println(softReference.get());

        byte[] bytes = new byte[1024 * 1024 * 10];

        System.out.println(softReference.get());
    }
}
