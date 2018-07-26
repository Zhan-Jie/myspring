package zhanjie.beans;

public class BeanDefinition {
    private String className;
    private Class clazz;
    private Object bean;
    private Properties properties;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public void setClassName(String name) {
        this.className = name;
        try {
            this.clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Class getBeanClazz() {
        return clazz;
    }

    public String getClassName() {
        return className;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
