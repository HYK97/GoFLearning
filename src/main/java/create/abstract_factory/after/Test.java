package create.abstract_factory.after;

public class Test {
    public static void main(String[] args) {

        WhiteShipFactory white = new WhiteShipFactory(new WhiteShipPartsFactory());

        System.out.println("white.createShip() = " + white.createShip());

    }
}
