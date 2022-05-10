package create.factory_method.after;

public class BlackShipFactory implements ShipFactory {


    @Override
    public Ship createShip() {
        return new BlackShip();
    }
}
