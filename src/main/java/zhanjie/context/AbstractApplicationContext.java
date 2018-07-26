package zhanjie.context;

import zhanjie.beans.factory.AbstractBeanFactory;

import java.io.IOException;

public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws IOException {

    }

    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }
}
