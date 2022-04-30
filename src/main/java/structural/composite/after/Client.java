package structural.composite.after;

public class Client {

    public static void main(String[] args) {
        Item doranBlade = new Item("도란검", 450);
        Item healPotion = new Item("체력 물약", 50);

        Bag bag = new Bag();
        bag.add(doranBlade);
        bag.add(healPotion);

        Client client = new Client();
        client.printPrice(doranBlade);
        client.printPrice(bag);
    }

    //하나의 component 인터페이스에 정의된 메서드를 이용해서 사용 가능
    private void printPrice(Component item) {
        System.out.println(item.getPrice());
    }


}
