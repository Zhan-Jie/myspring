package zhanjie;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
        BeanFactory factory = new BeanFactory();
        BeanDefinition hello = new BeanDefinition(new HelloService());
        factory.registerBeanDefinition("hello", hello);
        HelloService service = (HelloService) factory.getBean("hello");
        service.doHello();
    }
}
