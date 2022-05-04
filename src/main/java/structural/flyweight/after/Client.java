package structural.flyweight.after;

public class Client {

    public static void main(String[] args) {

        FontFactory fontFactory = new FontFactory();

        //Character 속성중 자주 변하는 속성인 Color , Name 속성 외에
        //FontSize 및 FontFamily는 FlyWeight 패턴을 이용해 메모리 사용을 줄임
        Character c1 = new Character('a', "red", fontFactory.getFont("nanm:12"));
        Character c2 = new Character('b', "yellow", fontFactory.getFont("nanm:13"));
        Character c3 = new Character('c', "blue", fontFactory.getFont("nanm:12"));

        System.out.println("c1 = " + c1.toString());
        System.out.println("c2 = " + c2.toString());
        System.out.println("c3 = " + c3.toString());
        fontFactory.getFont("nanm:12").getInformation();

    }
}
