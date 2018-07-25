package zhanjie.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import zhanjie.AbstractBeanDefinitionReader;
import zhanjie.BeanDefinition;
import zhanjie.Properties;
import zhanjie.Property;
import zhanjie.io.Resource;
import zhanjie.io.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XMLBeanDefinitionReader extends AbstractBeanDefinitionReader{

    public XMLBeanDefinitionReader(ResourceLoader loader) {
        super(loader);
    }

    @Override
    public void loadBeanDefinition(String location) throws IOException{
        Resource resource = getResourceLoader().getResource(location);
        InputStream input = resource.getInputStream();
        try {
            parseBeanDefinition(input);
        } catch (ParserConfigurationException | SAXException e) {
            throw new IOException(e);
        }
    }

    private void parseBeanDefinition(InputStream input) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(input);
        Element root = doc.getDocumentElement();
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);
            if (child instanceof Element) {
                Element ele = (Element) child;
                String name = ele.getAttribute("name");
                String clazz = ele.getAttribute("class");

                BeanDefinition definition = new BeanDefinition();
                definition.setClassName(clazz);
                injectProperties(ele, definition);

                putBeanDefinition(name, definition);
            }
        }
    }

    private void injectProperties(Element ele, BeanDefinition definition) {
        NodeList props = ele.getElementsByTagName("property");
        Properties properties = new Properties();
        for (int i = 0; i < props.getLength(); ++i) {
            Node prop = props.item(i);
            if (prop instanceof Element) {
                Element p = (Element) prop;
                String name = p.getAttribute("name");
                String value = p.getAttribute("value");
                properties.addProperty(new Property(name, value));
            }
        }
        definition.setProperties(properties);
    }
}
