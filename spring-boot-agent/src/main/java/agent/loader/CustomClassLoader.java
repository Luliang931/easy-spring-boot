package agent.loader;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: luliangliang
 * @Date: 2021/2/2 8:12 下午
 */
public class CustomClassLoader extends ClassLoader{
    private String path;
    private byte[] byteCodes;

    public CustomClassLoader(String path){
        this.path = path;
    }

    @SneakyThrows
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {

        Class<?> clazz = null;
        // 获取class文件字节码数组
        byteCodes = this.getByteCodes();
        System.out.println("byteCodes:"+byteCodes.length);

        if (byteCodes != null){
            clazz = defineClass(name, byteCodes, 0, byteCodes.length);
        }


        return clazz;
    }


    // 可以做些操作：加解密等
    private byte[] getByteCodes() throws IOException {
        File file = new File(path);
        if (file.exists()){
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];

            int size = 0;
            while ((size = in.read(buffer)) != -1){
                out.write(buffer,0,size);
            }

            return out.toByteArray();
        }

        return null;
    }
}
