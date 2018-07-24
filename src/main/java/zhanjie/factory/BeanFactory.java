package zhanjie.factory;


import zhanjie.BeanDefinition;

public interface BeanFactory {
    Object getBean(String name);
    void registerBeanDefinition(String name, BeanDefinition definition);
}
