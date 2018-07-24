package zhanjie;

import org.junit.Test;
import zhanjie.factory.AutowireCapableBeanFactory;
import zhanjie.factory.BeanFactory;

/**
 * Unit test for simple App.
 */
public class BeanFactoryTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        // Bean factory
        BeanFactory factory = new AutowireCapableBeanFactory();
        // Bean definition
        BeanDefinition hello = new BeanDefinition();
        hello.setClassName("zhanjie.HelloService");
        // add bean properties
        Properties props = new Properties();
        props.addProperty(new Property("text", "Are you ok?"));
        hello.setProperties(props);

        factory.registerBeanDefinition("helloService", hello);

        HelloService service = (HelloService) factory.getBean("helloService");
        service.doHello();
    }
}
