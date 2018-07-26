package zhanjie.beans.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import zhanjie.*;
import zhanjie.beans.AbstractBeanDefinitionReader;
import zhanjie.beans.BeanDefinition;
import zhanjie.beans.Properties;
import zhanjie.beans.Property;
import zhanjie.beans.io.Resource;
import zhanjie.beans.io.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XMLBeanDefinitionReader extends AbstractBeanDefinitionReader {

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

    private void injectProperties(Element ele, BeanDefinition definition) throws IOException{
        NodeList props = ele.getElementsByTagName("property");
        Properties properties = new Properties();
        for (int i = 0; i < props.getLength(); ++i) {
            Node prop = props.item(i);
            if (prop instanceof Element) {
                Element p = (Element) prop;
                String name = p.getAttribute("name");
                String value = p.getAttribute("value");
                if (value == null || value.trim().isEmpty()) {
                    String ref = p.getAttribute("ref");
                    if (ref == null || ref.trim().isEmpty()) {
                        throw new IOException("'value' or 'ref' attribute is required in <property> element");
                    }
                    BeanReference reference = new BeanReference();
                    reference.setName(ref);
                    properties.addProperty(new Property(name, reference));
                } else {
                    properties.addProperty(new Property(name, value));
                }
            }
        }
        definition.setProperties(properties);
    }
}
