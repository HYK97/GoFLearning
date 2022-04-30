package structural.composite.after;

public class Item implements Component {

    private String name;

    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }


    //공통기능
    @Override
    public int getPrice() {
        return this.price;
    }
}