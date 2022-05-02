package structural.flyweight.after;

public class Client {

    public static void main(String[] args) {
        FontFactory fontFactory = new FontFactory();

        Character c1 = new Character('a', "blue", fontFactory.getFont("nanm:12"));
        Character c2 = new Character('b', "blue", fontFactory.getFont("nanm:13"));
        Character c3 = new Character('c', "blue", fontFactory.getFont("nanm:12"));

    }
}
