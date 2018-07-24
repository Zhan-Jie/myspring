package zhanjie;

public class BeanDefinition {
    private String className;
    private Class clazz;
    private Object bean;

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

    public Class getClazz() {
        return clazz;
    }

    public String getClassName() {
        return className;
    }
}
