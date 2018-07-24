package zhanjie;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    private Map<String, BeanDefinition> container = new HashMap<>();

    public Object getBean(String name) {
        return container.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition definition) {
        container.put(name, definition);
    }
}
