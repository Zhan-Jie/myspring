package zhanjie;

import org.junit.Test;
import zhanjie.factory.AutowireCapableBeanFactory;
import zhanjie.factory.BeanFactory;
import zhanjie.io.ResourceLoader;
import zhanjie.xml.XMLBeanDefinitionReader;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.fail;

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
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(new ResourceLoader());
        try {
            reader.loadBeanDefinition("beans.xml");
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        BeanFactory factory = new AutowireCapableBeanFactory();
        for(Map.Entry<String, BeanDefinition> entry : reader.getRegistry().entrySet()) {
            factory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }

        HelloService service = (HelloService) factory.getBean("helloService");
        service.doHello();
    }
}
