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
        BeanFactory factory = new AutowireCapableBeanFactory();
        BeanDefinition hello = new BeanDefinition();
        hello.setClassName("zhanjie.HelloService");
        factory.registerBeanDefinition("helloService", hello);
        HelloService service = (HelloService) factory.getBean("helloService");
        service.doHello();
    }
}
