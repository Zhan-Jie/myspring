package zhanjie.factory;

import zhanjie.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanFactory implements BeanFactory {
    protected Map<String, BeanDefinition> definitions = new HashMap<>();

    @Override
    public Object getBean(String name) {
        return definitions.get(name).getBean();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition definition) {
        definition.setBean(doCreateBean(definition));
        this.definitions.put(name, definition);
    }

    protected abstract Object doCreateBean(BeanDefinition definition);
}
