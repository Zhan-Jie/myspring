package zhanjie.beans;

import java.util.ArrayList;
import java.util.List;

public class Properties {
    private List<Property> properties = new ArrayList<>();

    public List<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }
}
