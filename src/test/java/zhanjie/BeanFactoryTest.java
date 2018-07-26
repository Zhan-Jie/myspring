package zhanjie;

import org.junit.Test;
import zhanjie.context.ApplicationContext;
import zhanjie.context.ClassPathXmlApplicationContext;

import java.io.IOException;

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
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            HelloService service = (HelloService) context.getBean("helloService");
            service.doHello();
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }
}
