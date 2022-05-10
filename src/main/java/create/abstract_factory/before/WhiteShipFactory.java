package create.abstract_factory.before;


import create.factory_method.after.Ship;
import create.factory_method.after.ShipFactory;
import create.factory_method.after.WhiteShip;

public class WhiteShipFactory implements ShipFactory {

    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(new WhiteAnchor());
        ship.setWheel(new WhiteWheel());
        return ship;
    }
}
