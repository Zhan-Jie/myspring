package zhanjie;

public class HelloService {
    private String text;
    private ShabiService shabi;

    public void doHello() {
        System.out.println(text);
        System.out.println("sha bi is: " + shabi.getShabi());
    }
}
