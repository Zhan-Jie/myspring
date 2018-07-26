package zhanjie.beans.factory;

import zhanjie.beans.BeanDefinition;
import zhanjie.BeanReference;
import zhanjie.beans.Property;

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
                Object value = p.getValue();
                if (value instanceof BeanReference) {
                    BeanReference ref = (BeanReference) value;
                    value = getBean(ref.getName());
                    ref.setBean(value);
                }
                field.set(bean, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
