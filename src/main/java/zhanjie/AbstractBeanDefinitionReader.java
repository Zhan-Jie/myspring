package zhanjie;

import zhanjie.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private Map<String, BeanDefinition> registry;
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader loader) {
        this.resourceLoader = loader;
        this.registry = new HashMap<>();
    }

    public Map<String, BeanDefinition> getRegistry(){
        return registry;
    }

    protected void putBeanDefinition(String name, BeanDefinition definition){
        registry.put(name, definition);
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
