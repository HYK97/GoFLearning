package abstract_factory.after;


import abstract_factory.before.WhiteAnchor;
import abstract_factory.before.WhiteWheel;
import factorymethod.after.Ship;
import factorymethod.after.ShipFactory;
import factorymethod.after.WhiteShip;

public class WhiteShipFactory implements ShipFactory {


    private ShipPartsFactory shipPartsFactory;

    public WhiteShipFactory(ShipPartsFactory shipPartsFactory) { //shipPartsFactory를 DI해줌
        this.shipPartsFactory = shipPartsFactory;
    }

    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(shipPartsFactory.createAnchor());
        ship.setWheel(shipPartsFactory.createWheel());
        return ship;
    }
}
