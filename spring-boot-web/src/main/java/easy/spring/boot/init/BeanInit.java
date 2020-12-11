package easy.spring.boot.init;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationStartupAware;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author luliangliang
 */
@Component
public class BeanInit implements ApplicationRunner, InitializingBean, ApplicationContextAware, BeanClassLoaderAware, BeanNameAware, BeanFactoryAware, DisposableBean {

    /**
     * id
     */
    private Long id;

    /**
     * bean名称
     */
    private String beanName;

    /**
     * bean创建工厂
     */
    private BeanFactory beanFactory;

    /**
     * 应用上下文
     */
    private ApplicationContext applicationContext;

    /**
     * 类加载器
     */
    private ClassLoader classLoader;

    /**
     * 类初始化静态代码块
     */
    static {
        System.out.println("beanInit test execute static method init ....");
    }

    /**
     * 构造器
     */
    public BeanInit(){
        this.id = 1L;
        System.out.println("beanInit test execute Construct init ....");
    }

    @PostConstruct
    public void init(){
        //BeanPostProcessors.postProcessBeforeInitialization()
        System.out.println("beanInit test execute PostConstruct init ....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //InitializingBean
        System.out.println("beanInit test execute InitializingBean init .....");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //ApplicationRunner
        System.out.println("beanInit test execute ApplicationRunner init ....");
    }

    public void initMethod() {
        //@Bean init-method
        System.out.println("beanInit test execute class init method ....");
    }

    @Override
    public void setBeanName(String name) {
        //BeanNameAware
        System.out.println("beanInit setBeanName: " + name);
        this.beanName = name;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        //BeanFactoryAware
        System.out.println("beanInit beanFactory: " + beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    public void destroy() throws Exception {
        //DisposableBean
        System.out.println("beanInit test execute DisposableBean init ....");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //ApplicationContextAware
        System.out.println("beanInit applicationContext: " + applicationContext);
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        //BeanClassLoaderAware
        System.out.println("beanInit classLoader: " + classLoader);
        this.classLoader = classLoader;
    }
}
