package zhanjie.factory;

import zhanjie.BeanDefinition;
import zhanjie.Property;

import java.lang.reflect.Field;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object doCreateBean(BeanDefinition definition) {
        try {
            Object bean = createBeanInstance(definition);
            injectBeanProperties(bean, definition);
            return bean;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object createBeanInstance(BeanDefinition definition) throws IllegalAccessException, InstantiationException {
        return definition.getBeanClazz().newInstance();
    }

    private void injectBeanProperties(Object bean, BeanDefinition definition) {
        for (Property p : definition.getProperties().getProperties()) {
            try {
                Field field = bean.getClass().getDeclaredField(p.getName());
                field.setAccessible(true);
                field.set(bean, p.getValue());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
