package zhanjie;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface BeanDefinitionReader {
    void loadBeanDefinition(String location) throws IOException;
}
