package zhanjie.factory;

import zhanjie.BeanDefinition;
import zhanjie.BeanReference;
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
                System.out.printf("inject property '%s' of class %s : %s\n", p.getName(), bean.getClass().getName(), p.getValue().getClass().getName());
                Object value = p.getValue();
                if (value instanceof String){
                    field.set(bean, value);
                } else if (value instanceof BeanReference) {
                    BeanReference ref = (BeanReference) value;
                    Object refBean = getBean(ref.getName());
                    ref.setBean(refBean);
                    field.set(bean, refBean);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
