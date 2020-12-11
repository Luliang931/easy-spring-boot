package easy.spring.boot.init;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class PostProcessor implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //BeanPostProcessor
        System.out.println("Test execute BeanPostProcessor before init ...."+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //BeanPostProcessor
        System.out.println("Test execute BeanPostProcessor after init ...."+beanName);
        return bean;
    }
}
