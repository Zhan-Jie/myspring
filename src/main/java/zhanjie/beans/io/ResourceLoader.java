package zhanjie.beans.io;

public class ResourceLoader {
    public Resource getResource(String location) {
        return new UrlResource(this.getClass().getClassLoader().getResource(location));
    }
}
