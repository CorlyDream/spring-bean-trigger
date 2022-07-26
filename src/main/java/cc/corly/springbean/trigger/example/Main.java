package cc.corly.springbean.trigger.example;

import cc.corly.springbean.trigger.BeanHttpExpose;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;


public class Main {

    public static void main(String[] args) throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerSingleton("demoBean", new DemoBean());
        BeanHttpExpose expose = BeanHttpExpose.build(beanFactory);
        Thread.currentThread().join();
    }
}
