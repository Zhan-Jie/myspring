package zhanjie.context;

import zhanjie.beans.BeanDefinition;
import zhanjie.beans.factory.AbstractBeanFactory;
import zhanjie.beans.factory.AutowireCapableBeanFactory;
import zhanjie.beans.io.ResourceLoader;
import zhanjie.beans.xml.XMLBeanDefinitionReader;

import java.io.IOException;
import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
    private String configLocation;


    public ClassPathXmlApplicationContext(String location) throws IOException {
        this(location, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String location, AbstractBeanFactory beanFactory) throws IOException {
        super(beanFactory);
        this.configLocation = location;
        refresh();
    }

    @Override
    public void refresh() throws IOException {
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinition(configLocation);
        for (Map.Entry<String, BeanDefinition> e : reader.getRegistry().entrySet()) {
             super.beanFactory.registerBeanDefinition(e.getKey(), e.getValue());
        }
    }

}
