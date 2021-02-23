package agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Date;


/**
 * @Author: luliangliang
 * @Date: 2021/1/27 5:57 下午
 */
public class PreMainTraceAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs : " + agentArgs);
        Date date = new Date();
        System.out.println(date.toString());
        inst.addTransformer(new DefineTransformer(), true);
    }

    static class DefineTransformer implements ClassFileTransformer{
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            String dateStr = "java/util/Date";
            if (dateStr.equals(className)){
                System.out.println("enter date : ");
                try {
                    ClassPool classPool = ClassPool.getDefault();
                    CtClass clazz = classPool.get(dateStr);
                    CtMethod convertToAbbr = clazz.getDeclaredMethod("convertToAbbr");

                    String methodBody = "{sb.append(Character.toUpperCase(name.charAt(0)));" +
                            "sb.append(name.charAt(1)).append(name.charAt(2));" +
                            "System.out.println(\"sb.toString()\");" +
                            "return sb;}";

                    convertToAbbr.setBody(methodBody);

                    byte[] byteCodes = clazz.toBytecode();
                    clazz.detach();

                    return byteCodes;
                }catch (Exception ex){
                    System.out.println("transform exception cause:"+ex.getStackTrace());
                }
            }
            return null;
        }
    }
}

