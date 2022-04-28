package create.abstract_factory.before;


import create.factorymethod.after.Ship;
import create.factorymethod.after.ShipFactory;
import create.factorymethod.after.WhiteShip;

public class WhiteShipFactory implements ShipFactory {

    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(new WhiteAnchor());
        ship.setWheel(new WhiteWheel());
        return ship;
    }
}
