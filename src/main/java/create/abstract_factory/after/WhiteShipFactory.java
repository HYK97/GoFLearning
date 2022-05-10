package create.abstract_factory.after;


import create.factory_method.after.Ship;
import create.factory_method.after.ShipFactory;
import create.factory_method.after.WhiteShip;

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
